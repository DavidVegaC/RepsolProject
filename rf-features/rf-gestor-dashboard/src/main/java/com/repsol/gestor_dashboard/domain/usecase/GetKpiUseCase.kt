package com.repsol.gestor_dashboard.domain.usecase

import com.repsol.core_domain.common.use_case.SimpleUseCase
import com.repsol.gestor_dashboard.domain.repository.CardsRepository
import com.repsol.gestor_dashboard.domain.result.GetKpiResult
import com.repsol.gestor_dashboard.domain.result.handleResult
import javax.inject.Inject

class GetKpiUseCase @Inject constructor(
    private val repository: CardsRepository,
): SimpleUseCase.OnlyResult<GetKpiResult> {

    override suspend fun invoke(): GetKpiResult {
        //val clientId: String = SessionStorage.getString(UserSession.ID_CLIENT).orEmpty()
        val clientId = "0348117784"
        return repository.getKpi(clientId).handleResult()
    }

}