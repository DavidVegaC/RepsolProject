package com.repsol.core_data.common.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class VehicleDataResponse(
    val listVehicles: List<VehicleResponse>,
    val pagination: PaginationResponse,
)