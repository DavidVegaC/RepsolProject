package com.repsol.auth.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import com.repsol.core_data.common.remote.dto.response.ErrorManagerResponse
import kotlinx.serialization.Serializable

@Serializable
class UserInformationResponse(
    @SerializedName("data")
    val data: DataResponse,
    @SerializedName("error_manager")
    val error_manager: ErrorManagerResponse,
)