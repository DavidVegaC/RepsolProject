package com.repsol.auth.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import com.repsol.auth.ui.login.LoginScreen
import com.repsol.auth.ui.onboarding.OnboardingScreen
import com.repsol.navigation.MainGraph

fun NavGraphBuilder.authNavigation() {
    navigation<MainGraph.AuthModule>(
        startDestination = AuthGraph.Onboarding
    ) {
        composable<AuthGraph.Login> {
            LoginScreen()
        }
        composable<AuthGraph.Onboarding> {
            OnboardingScreen()
        }
    }
}