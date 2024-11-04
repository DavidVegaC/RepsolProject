package com.repsol.auth.domain.usecase

import com.repsol.auth.data.model.OnboardingPage
import com.repsol.auth.domain.repository.OnboardingRepository
import com.repsol.core_domain.common.use_case.SimpleUseCase
import javax.inject.Inject

class GetOnboardingPageUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
): SimpleUseCase.OnlyResult<List<OnboardingPage>> {

    override suspend fun invoke(): List<OnboardingPage> {
        return onboardingRepository.getOnboardingPages()
    }
}