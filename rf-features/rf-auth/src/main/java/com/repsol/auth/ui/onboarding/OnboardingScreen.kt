package com.repsol.auth.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.repsol.auth.ui.AuthGraph
import com.repsol.core_ui.stateful.Stateful
import com.repsol.navigation.MainGraph
import com.repsol.navigation.core.localNavController
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiState as UiState
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiEvent as UiEvent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiIntent as UiIntent

@Composable
fun OnboardingScreen() = Stateful<OnboardingViewModel> {
    val navController = localNavController()
    val uiState by uiState()
    OnUiEvent {
        when(it) {
            is UiEvent.GoToLogin -> {
                navController.navigate(AuthGraph.Login)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Onboarding")
    }
}