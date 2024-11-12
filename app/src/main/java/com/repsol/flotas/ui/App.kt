package com.repsol.flotas.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.repsol.feature_manager.MainNavigation
import com.repsol.flotas.ui.theme.RepsolFlotasTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun App(
    loadingFlow: Flow<Boolean>
) {
    RepsolFlotasTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            MainNavigation()
            val isLoading by loadingFlow.collectAsState(initial = false)
            if (isLoading) {
                Dialog(
                    onDismissRequest = {}
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}