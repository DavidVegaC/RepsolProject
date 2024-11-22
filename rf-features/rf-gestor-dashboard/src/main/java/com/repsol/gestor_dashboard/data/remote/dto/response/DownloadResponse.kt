package com.repsol.gestor_dashboard.data.remote.dto.response

import com.repsol.core_data.common.remote.dto.response.ErrorManagerResponse
import kotlinx.serialization.Serializable

@Serializable
class DownloadResponse(
    val data: String?,
    val error_manager: ErrorManagerResponse,
)