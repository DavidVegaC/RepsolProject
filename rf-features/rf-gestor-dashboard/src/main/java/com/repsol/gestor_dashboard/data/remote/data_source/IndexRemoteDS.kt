package com.repsol.gestor_dashboard.data.remote.data_source

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.data.remote.dto.request.DownloadRequest
import com.repsol.gestor_dashboard.data.remote.dto.response.CreditBalanceResponse
import com.repsol.gestor_dashboard.data.remote.dto.response.DownloadResponse

interface IndexRemoteDS {

    suspend fun postDownload(clientId: String, request: DownloadRequest): DataOutput<DownloadResponse>

    suspend fun getCreditBalance(clientId: String): DataOutput<CreditBalanceResponse>
}