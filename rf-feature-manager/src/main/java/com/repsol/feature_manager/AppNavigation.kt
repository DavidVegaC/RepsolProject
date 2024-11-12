package com.repsol.feature_manager

import androidx.compose.runtime.Composable
import com.repsol.auth.ui.authNavigation
import com.repsol.home.ui.homeNavigation
import com.repsol.navigation.MainGraph
import com.repsol.navigation.core.NavHost

@Composable
fun MainNavigation() {
    NavHost(
        startDestination = MainGraph.AuthModule
    ) {
        authNavigation()
        managerHomeNavigation()
    }
}