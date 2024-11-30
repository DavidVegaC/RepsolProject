package com.repsol.gestor_dashboard.data.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.data.mapper.CardsMapper.toKpi
import com.repsol.gestor_dashboard.data.remote.data_source.cards.CardsRemoteDs
import com.repsol.gestor_dashboard.domain.entity.KpiData
import com.repsol.gestor_dashboard.domain.repository.CardsRepository
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val remote: CardsRemoteDs,
): CardsRepository {

    override suspend fun getKpi(clientId: String): DataOutput<KpiData> {
        val dataOutput = remote.getKpi(clientId)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }

        val kpiData: KpiData = toKpi(dataOutput.asSuccessful().data)
        return Output.Success(kpiData)
    }
}