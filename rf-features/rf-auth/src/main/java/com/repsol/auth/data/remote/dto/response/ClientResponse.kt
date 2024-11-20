package com.repsol.auth.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class ClientResponse(
    @SerializedName("listProfile")
    val listProfile: List<ProfileResponse?>?,
    @SerializedName("idClient")
    val idClient: String?,
    @SerializedName("businessName")
    val businessName: String?,
)