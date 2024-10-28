package com.repsol.auth.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.repsol.auth.ui.login.interactor.LoginUiState as UiState
import com.repsol.auth.ui.login.interactor.LoginUiEvent as UiEvent
import com.repsol.auth.ui.login.interactor.LoginUiIntent as UiIntent

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
            value = uiState.username,
            onValueChange = {
                setUiState {
                    copy(username = it)
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