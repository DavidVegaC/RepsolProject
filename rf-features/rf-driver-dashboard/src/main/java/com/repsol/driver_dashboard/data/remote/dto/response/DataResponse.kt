package com.repsol.driver_dashboard.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class DataResponse(
    val cardSearchListDto: List<CardResponse>,
)