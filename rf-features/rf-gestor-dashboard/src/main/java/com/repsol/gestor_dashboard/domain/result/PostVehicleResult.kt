package com.repsol.gestor_dashboard.domain.result

import com.repsol.core_data.common.remote.DataError
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.domain.entity.VehicleItem
import com.repsol.gestor_dashboard.domain.entity.VehicleList
import com.repsol.railway.isSuccessful

sealed class PostVehicleResult {
    data class Success(val data: VehicleItem) : PostVehicleResult()
    data class Error(val message: String) : PostVehicleResult()
    data class ServiceError(val message: String) : PostVehicleResult()
}

fun DataOutput<VehicleList>.handleResult(): PostVehicleResult {
    if (this.isSuccessful()) {
        val vehicleList: VehicleList = this.data
        val errorManager = vehicleList.errorManager
        if (errorManager.code.contentEquals("0").not()) {
            return PostVehicleResult.ServiceError(message = errorManager.message.orEmpty())
        }

        val vehicle: VehicleItem = vehicleList.items.firstOrNull()
            ?: return PostVehicleResult.ServiceError(message = "No existen vehiculos")
        return PostVehicleResult.Success(vehicle)
    } else {
        val error: DataError.Default = error as DataError.Default
        return PostVehicleResult.Error(message = error.message)
    }
}