package com.repsol.auth.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class ProfileResponse(
    @SerializedName("idProfile")
    val idProfile: Int,
    @SerializedName("nameProfile")
    val nameProfile: String?,
)