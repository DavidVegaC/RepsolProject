package com.repsol.core_data.common.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class DataResponse(
    val cardSearchListDto: List<CardResponse>,
)