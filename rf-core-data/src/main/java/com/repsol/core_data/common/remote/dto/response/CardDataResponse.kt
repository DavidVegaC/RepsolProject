package com.repsol.core_data.common.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class CardDataResponse(
    val cardSearchListDto: List<CardResponse>,
    val pagination: PaginationResponse,
)