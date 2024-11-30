package com.repsol.gestor_dashboard.data.remote.dto.response.kpi

import kotlinx.serialization.Serializable

@Serializable
class DataResponse(
    val countActive: Int,
    val countInactive: Int,
    val countCanceled: Int,
)