package com.repsol.auth.data.repository

import com.repsol.auth.data.model.OnboardingPage
import com.repsol.auth.domain.repository.OnboardingRepository
import com.repsol.auth.ui.login.interactor.LoginUiState.Companion.IS_ONBOARDED
import com.repsol.core_domain.storage.LocalStorage
import com.repsol.rf_assets.R
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(): OnboardingRepository {

    override suspend fun getOnboardingPages(): List<OnboardingPage> {
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

    override suspend fun changeValueOnboarded() {
        LocalStorage.setBoolean(IS_ONBOARDED, true)
    }
}