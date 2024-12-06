package com.repsol.core_data.common.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class VehicleListRequest(
    val plate: String,
    val codeBrand: List<String>,
    val codeType: List<String>,
    val model: String,
    val year: String,
    val fuel: String,
    val codeState: Int,
    val tankCapacity: Int,
    val performance: Int,
    val associatedDriver: Int,
    val pageNumber: Int,
    val pageSize: Int,
) {

    companion object {

        val defaultRequest = VehicleListRequest(
            plate = "",
            codeBrand = emptyList(),
            codeType = emptyList(),
            model = "",
            year = "",
            fuel = "",
            codeState = -1,
            tankCapacity = -1,
            performance = -1,
            associatedDriver = -1,
            pageNumber = 1,
            pageSize = 100,
        )
    }
}