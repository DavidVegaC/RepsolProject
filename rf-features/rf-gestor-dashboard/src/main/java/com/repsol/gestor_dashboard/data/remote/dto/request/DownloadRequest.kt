package com.repsol.gestor_dashboard.data.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
class DownloadRequest(
    val tipored: String,
    val tipocombu: String,
    val ciudad: String,
    val zona: String,
    val pageNumber: Int,
    val pageSize: Int,
)