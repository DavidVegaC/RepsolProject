package com.repsol.core_data.common.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class ErrorManagerResponse(
    @SerializedName("errorNumber")
    val errorNumber: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("severity")
    val severity: Int,
)