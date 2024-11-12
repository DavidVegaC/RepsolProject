package com.repsol.auth.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.repsol.auth.ui.login.LoginScreen
import com.repsol.auth.ui.login.interactor.LoginUiState
import com.repsol.auth.ui.onboarding.OnboardingScreen
import com.repsol.core_domain.storage.LocalStorage
import com.repsol.navigation.MainGraph
import kotlinx.coroutines.runBlocking

fun NavGraphBuilder.authNavigation() {
    navigation<MainGraph.AuthModule>(
        startDestination = when {
            isOnboarded() -> AuthGraph.Login
            else -> AuthGraph.Onboarding
        }
    ) {
        composable<AuthGraph.Onboarding> {
            OnboardingScreen()
        }
        composable<AuthGraph.Login> {
            LoginScreen()
        }
    }
}

private fun isOnboarded() = runBlocking {
    LocalStorage.getBoolean(LoginUiState.IS_ONBOARDED) == true
}