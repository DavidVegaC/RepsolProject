package com.repsol.core_data.common.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class VehicleResponse(
    val idVehicle: Int,
    val plate: String,
    val brand: String,
    val type: String,
    val model: String,
    val year: String,
    val fuel: String,
    val codeState: Int,
    val tankCapacity: String,
    val performance: String,
    val associatedDriver: String,
    val modificationDate: String,
    val terminationDate: String,
)