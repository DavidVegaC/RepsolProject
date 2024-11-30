package com.repsol.core_data.common.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class CardListResponse(
    @SerializedName("data")
    val data: DataResponse,
    @SerializedName("error_manager")
    val error_manager: ErrorManagerResponse,
)