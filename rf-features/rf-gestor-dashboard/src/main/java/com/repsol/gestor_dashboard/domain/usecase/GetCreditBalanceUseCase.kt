package com.repsol.gestor_dashboard.domain.usecase

import com.repsol.core_domain.common.use_case.SimpleUseCase
import com.repsol.gestor_dashboard.domain.repository.CreditBalanceRepository
import com.repsol.gestor_dashboard.domain.result.GetCreditBalanceResult
import com.repsol.gestor_dashboard.domain.result.handleResult
import javax.inject.Inject

class GetCreditBalanceUseCase @Inject constructor(
    private val repository: CreditBalanceRepository,
): SimpleUseCase.ParamsAndResult<String, GetCreditBalanceResult> {

    override suspend fun invoke(params: String): GetCreditBalanceResult {
        return repository.getCreditBalance(params).handleResult()
    }


}