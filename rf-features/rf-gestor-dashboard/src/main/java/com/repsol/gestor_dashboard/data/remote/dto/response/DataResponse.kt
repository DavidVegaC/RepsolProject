package com.repsol.gestor_dashboard.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class DataResponse(
    @SerializedName("balance")
    val balance: String,
    @SerializedName("lineCred")
    val lineCred: String,
    @SerializedName("paymentDeadLine")
    val paymentDeadLine: String,
    @SerializedName("excedido")
    val excedido: String,
    @SerializedName("fechaCaducidad")
    val fechaCaducidad: String,
    @SerializedName("recargar")
    val recargar: Boolean,
    @SerializedName("importe")
    val importe: String,
    @SerializedName("deuda")
    val deuda: String,
)