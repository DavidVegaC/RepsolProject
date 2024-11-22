package com.repsol.gestor_dashboard.data.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.data.mapper.IndexMapper.toCreditBalance
import com.repsol.gestor_dashboard.data.mapper.IndexMapper.toDownload
import com.repsol.gestor_dashboard.data.remote.data_source.IndexRemoteDS
import com.repsol.gestor_dashboard.data.remote.dto.request.DownloadRequest
import com.repsol.gestor_dashboard.domain.entity.CreditBalance
import com.repsol.gestor_dashboard.domain.entity.Download
import com.repsol.gestor_dashboard.domain.repository.IndexRepository
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject

class IndexRepositoryImpl @Inject constructor(
    private val remote: IndexRemoteDS,
): IndexRepository {

    override suspend fun postDownload(clientId: String): DataOutput<Download> {
        val request = DownloadRequest("","","","", 1, 2147483647)
        val dataOutput = remote.postDownload(clientId, request)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }
        val download: Download = toDownload(dataOutput.asSuccessful().data)
        return Output.Success(download)
    }

    override suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalance> {
        val dataOutput = remote.getCreditBalance(clientId)
        if (dataOutput.isFailure()){
            return dataOutput.asFailure()
        }

        val creditBalance: CreditBalance = toCreditBalance(dataOutput.asSuccessful().data)

        return Output.Success(creditBalance)
    }
}