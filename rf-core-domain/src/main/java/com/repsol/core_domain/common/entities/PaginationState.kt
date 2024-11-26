package com.repsol.core_domain.common.entities

class PaginationState(
    val currentPage: Int,
    val totalRows: Int,
    val pageSize: Int,
    val totalPage: Int,
)