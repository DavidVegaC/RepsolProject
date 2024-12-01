package com.repsol.core_data.common.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class PaginationResponse(
    val currentPage: Int,
    val totalRows: Int,
    val pageSize: Int,
    val totalPage: Int,
)