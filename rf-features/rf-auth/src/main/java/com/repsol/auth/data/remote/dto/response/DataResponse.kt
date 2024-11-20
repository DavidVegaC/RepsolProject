package com.repsol.auth.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class DataResponse(
    @SerializedName("listClient")
    val listClient: List<ClientResponse?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("lastName")
    val lastName: String?,
)