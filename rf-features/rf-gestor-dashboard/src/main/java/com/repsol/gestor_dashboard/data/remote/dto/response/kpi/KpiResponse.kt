package com.repsol.gestor_dashboard.data.remote.dto.response.kpi

import com.google.gson.annotations.SerializedName
import com.repsol.core_data.common.remote.dto.response.ErrorManagerResponse
import kotlinx.serialization.Serializable

@Serializable
class KpiResponse(
    @SerializedName("data")
    val data: DataResponse,
    @SerializedName("error_manager")
    val error_manager: ErrorManagerResponse,
)