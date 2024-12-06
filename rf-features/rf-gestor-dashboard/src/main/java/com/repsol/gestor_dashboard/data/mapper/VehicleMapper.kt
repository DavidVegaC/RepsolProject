package com.repsol.gestor_dashboard.data.mapper

import com.repsol.core_data.common.remote.dto.response.VehicleListResponse
import com.repsol.core_data.common.remote.dto.response.VehicleResponse
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.gestor_dashboard.domain.entity.VehicleItem
import com.repsol.gestor_dashboard.domain.entity.VehicleList

object VehicleMapper {

    fun toVehicleList(response: VehicleListResponse): VehicleList {
        val error = GlobalError(
            code = response.error_manager.errorNumber.toString(),
            message = response.error_manager.description,
        )

        val items: List<VehicleItem> = response.data.listVehicles.map(::toVehicleItem)
        return VehicleList(
            items = items,
            errorManager = error
        )
    }

    private fun toVehicleItem(response: VehicleResponse): VehicleItem {

        return VehicleItem(
            idVehicle = response.idVehicle,
            plate = response.plate,
            brand = response.brand,
            type = response.type,
            model = response.model,
            year = response.year,
            fuel = response.fuel,
            codeState = response.codeState,
            tankCapacity = response.tankCapacity,
            performance = response.performance,
            associatedDriver = response.associatedDriver,
            modificationDate = response.modificationDate,
            terminationDate = response.terminationDate,
        )
    }
}