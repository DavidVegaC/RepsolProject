package com.repsol.auth.domain.usecase

import com.repsol.auth.domain.parameters.ValidateLoginParams
import com.repsol.auth.domain.repository.LoginRepository
import com.repsol.auth.domain.result.ValidateLoginResult
import com.repsol.auth.domain.result.handleResult
import com.repsol.core_domain.common.use_case.SimpleUseCase
import javax.inject.Inject

class ValidateLoginUseCase @Inject constructor(
    private val repository: LoginRepository,
): SimpleUseCase.ParamsAndResult<ValidateLoginParams, ValidateLoginResult> {

    override suspend fun invoke(params: ValidateLoginParams): ValidateLoginResult {
        return repository.loginWith(params.email, params.password).handleResult()
    }
}