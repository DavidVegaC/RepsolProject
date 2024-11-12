package com.repsol.auth.domain.usecase

import com.repsol.auth.domain.repository.OnboardingRepository
import com.repsol.core_domain.common.use_case.SimpleUseCase
import javax.inject.Inject

class ChangeValueOnboardedUseCase@Inject constructor(
    private val repository: OnboardingRepository
): SimpleUseCase.Empty {

    override suspend fun invoke() {
        repository.changeValueOnboarded()
    }
}