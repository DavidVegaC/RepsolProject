package com.repsol.auth.domain.usecase

import com.repsol.auth.data.model.OnboardingPage
import com.repsol.auth.data.repository.OnboardingRepository
import javax.inject.Inject

class GetOnboardingPageUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    fun execute(): List<OnboardingPage> {
        return onboardingRepository.getOnboardingPages()
    }
}