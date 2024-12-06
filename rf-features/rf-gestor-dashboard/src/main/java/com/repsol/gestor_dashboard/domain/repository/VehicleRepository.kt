package com.repsol.gestor_dashboard.domain.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.VehicleListRequest
import com.repsol.gestor_dashboard.domain.entity.VehicleList

interface VehicleRepository {

    suspend fun postVehicleList(
        clientId: String,
        request: VehicleListRequest,
    ): DataOutput<VehicleList>
}