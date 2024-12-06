package com.repsol.gestor_dashboard.data.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.VehicleListRequest
import com.repsol.core_data.common.remote.shared.data_source.SharedRemoteDS
import com.repsol.gestor_dashboard.data.mapper.VehicleMapper.toVehicleList
import com.repsol.gestor_dashboard.domain.entity.VehicleList
import com.repsol.gestor_dashboard.domain.repository.VehicleRepository
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val sharedRemoteDS: SharedRemoteDS,
): VehicleRepository {

    override suspend fun postVehicleList(
        clientId: String,
        request: VehicleListRequest,
    ): DataOutput<VehicleList> {
        val dataOutput = sharedRemoteDS.postVehicleList(clientId, request)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }

        val vehicleList: VehicleList = toVehicleList(dataOutput.asSuccessful().data)
        return Output.Success(vehicleList)
    }
}