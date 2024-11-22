package com.repsol.gestor_dashboard.domain.usecase

import com.repsol.core_domain.common.use_case.SimpleUseCase
import com.repsol.core_domain.storage.SessionStorage
import com.repsol.gestor_dashboard.domain.repository.IndexRepository
import com.repsol.gestor_dashboard.domain.result.GetCreditBalanceResult
import com.repsol.gestor_dashboard.domain.result.handleResult
import com.repsol.tools.utils.UserSession
import javax.inject.Inject

class GetCreditBalanceUseCase @Inject constructor(
    private val repository: IndexRepository,
): SimpleUseCase.OnlyResult<GetCreditBalanceResult> {

    override suspend fun invoke(): GetCreditBalanceResult {
        val clientId: String = SessionStorage.getString(UserSession.ID_CLIENT).orEmpty()
        //val clientId = "0348117784"
        return repository.getCreditBalance(clientId).handleResult()
    }
}