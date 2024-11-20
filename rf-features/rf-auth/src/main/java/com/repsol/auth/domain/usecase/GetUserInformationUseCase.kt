package com.repsol.auth.domain.usecase

import com.repsol.auth.domain.repository.LoginRepository
import com.repsol.auth.domain.result.GetUserInformationResult
import com.repsol.auth.domain.result.handleResult
import com.repsol.core_domain.common.use_case.SimpleUseCase
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val repository: LoginRepository,
): SimpleUseCase.ParamsAndResult<String, GetUserInformationResult> {

    override suspend fun invoke(params: String): GetUserInformationResult {
        return repository.getUserInformation(params).handleResult()
    }
}