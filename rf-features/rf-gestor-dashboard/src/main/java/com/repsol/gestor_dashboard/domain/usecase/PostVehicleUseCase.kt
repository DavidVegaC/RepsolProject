package com.repsol.gestor_dashboard.domain.usecase

import com.repsol.core_data.common.remote.dto.request.VehicleListRequest
import com.repsol.core_domain.common.use_case.SimpleUseCase
import com.repsol.gestor_dashboard.domain.repository.VehicleRepository
import com.repsol.gestor_dashboard.domain.result.PostVehicleResult
import com.repsol.gestor_dashboard.domain.result.handleResult
import javax.inject.Inject

class PostVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository,
): SimpleUseCase.ParamsAndResult<String, PostVehicleResult> {

    override suspend fun invoke(params: String): PostVehicleResult {
        //val clientId: String = SessionStorage.getString(UserSession.ID_CLIENT).orEmpty()
        val clientId = "0348117784"
        val request = VehicleListRequest.defaultRequest.copy(plate = params)
        return repository.postVehicleList(clientId, request).handleResult()
    }

}