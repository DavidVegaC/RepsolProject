package com.repsol.auth.data.repository

import com.repsol.auth.data.model.OnboardingPage
import com.repsol.rf_assets.R
import javax.inject.Inject

class OnboardingRepository @Inject constructor() {
    fun getOnboardingPages(): List<OnboardingPage> {
        return listOf(
            OnboardingPage(
                titleRes = R.string.onboarding_welcome,
                descriptionRes = R.string.onboarding_welcome_description,
                imageRes = R.drawable.ic_rocket
            ),
            OnboardingPage(
                titleRes = R.string.onboarding_card_information,
                descriptionRes = R.string.onboarding_card_information_description,
                imageRes = R.drawable.ic_card_onboarding
            ),
            OnboardingPage(
                titleRes = R.string.onboarding_route_tracking,
                descriptionRes = R.string.onboarding_route_tracking_description,
                imageRes = R.drawable.ic_route_tracking
            ),
            OnboardingPage(
                titleRes = R.string.onboarding_start_your_journey,
                descriptionRes = R.string.onboarding_start_your_journey_description,
                imageRes = R.drawable.ic_world
            )
        )
    }
}