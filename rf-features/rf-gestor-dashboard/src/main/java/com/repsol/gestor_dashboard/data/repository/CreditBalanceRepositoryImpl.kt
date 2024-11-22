package com.repsol.gestor_dashboard.data.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.data.mapper.CreditBalanceMapper.toCreditBalance
import com.repsol.gestor_dashboard.data.remote.data_source.CreditBalanceRemoteDS
import com.repsol.gestor_dashboard.domain.entity.CreditBalance
import com.repsol.gestor_dashboard.domain.repository.CreditBalanceRepository
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject

class CreditBalanceRepositoryImpl @Inject constructor(
    private val remote: CreditBalanceRemoteDS
): CreditBalanceRepository {

    override suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalance> {
        val dataOutput = remote.getCreditBalance(clientId)
        if (dataOutput.isFailure()){
            return dataOutput.asFailure()
        }

        val creditBalance: CreditBalance = toCreditBalance(dataOutput.asSuccessful().data)

        return Output.Success(creditBalance)
    }


}