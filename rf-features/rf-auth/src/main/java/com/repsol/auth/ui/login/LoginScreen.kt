package com.repsol.auth.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.navigation.MainGraph
import com.repsol.navigation.core.localNavController
import com.repsol.auth.ui.login.interactor.LoginUiEvent as UiEvent
import com.repsol.auth.ui.login.interactor.LoginUiIntent as UiIntent
import com.repsol.auth.ui.login.interactor.LoginUiState as UiState

@Composable
fun LoginScreen() = Stateful<LoginViewModel> {
    val navController = localNavController()
    val uiState by uiState()
    OnUiEvent {
        when(it) {
            is UiEvent.GoToHome -> {
                navController.navigate(MainGraph.HomeModule)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = uiState.email,
            onValueChange = {
                setUiState {
                    copy(email = it)
                }
            },
            label = {
                Text(text = "Username")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = uiState.password,
            onValueChange = {
                setUiState {
                    copy(password = it)
                }
            },
            label = {
                Text(text = "Password")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                execUiIntent(UiIntent.OnLoginClick)
            }
        ) {
            Text(text = "Login")
        }
    }
}

@Preview
@Composable
fun DefaultLoginScreenPreview() {
    ScreenPreview(uiState = UiState()) {
        LoginScreen()
    }
}