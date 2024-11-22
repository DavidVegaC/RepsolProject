package com.repsol.gestor_dashboard.data.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.safeApiCall
import com.repsol.gestor_dashboard.data.remote.api.IndexApi
import com.repsol.gestor_dashboard.data.remote.dto.request.DownloadRequest
import com.repsol.gestor_dashboard.data.remote.dto.response.CreditBalanceResponse
import com.repsol.gestor_dashboard.data.remote.dto.response.DownloadResponse
import javax.inject.Inject

class DefaultIndexRemoteDS @Inject constructor(
    private val api: IndexApi
): IndexRemoteDS {

    override suspend fun postDownload(
        clientId: String,
        request: DownloadRequest,
    ): DataOutput<DownloadResponse> {
        return safeApiCall { api.postDownload(clientId, request) }
    }

    override suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalanceResponse> {
        return safeApiCall { api.getCreditBalance(clientId) }
    }
}