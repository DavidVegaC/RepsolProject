package com.repsol.gestor_dashboard.data.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_data.common.remote.shared.data_source.SharedRemoteDS
import com.repsol.gestor_dashboard.data.mapper.CardsMapper.toCardList
import com.repsol.gestor_dashboard.data.mapper.CardsMapper.toKpi
import com.repsol.gestor_dashboard.data.remote.data_source.cards.CardsRemoteDs
import com.repsol.gestor_dashboard.domain.entity.CardList
import com.repsol.gestor_dashboard.domain.entity.KpiData
import com.repsol.gestor_dashboard.domain.repository.CardsRepository
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val remote: CardsRemoteDs,
    private val sharedRemoteDS: SharedRemoteDS,
): CardsRepository {

    override suspend fun getKpi(clientId: String): DataOutput<KpiData> {
        val dataOutput = remote.getKpi(clientId)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }

        val kpiData: KpiData = toKpi(dataOutput.asSuccessful().data)
        return Output.Success(kpiData)
    }

    override suspend fun postCardList(
        clientId: String,
        userEmail: String,
        request: CardListRequest,
    ): DataOutput<CardList> {
        val dataOutput = sharedRemoteDS.postCardList(clientId, userEmail, request)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }

        val cardList: CardList = toCardList(dataOutput.asSuccessful().data)
        return Output.Success(cardList)
    }
}