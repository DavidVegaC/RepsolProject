package com.repsol.feature_manager

import androidx.compose.runtime.Composable
import com.repsol.auth.ui.authNavigation
import com.repsol.gestor_dashboard.ui.gestorDashboardNavigation
import com.repsol.driver_dashboard.ui.driverDashboardNavigation
import com.repsol.navigation.MainGraph
import com.repsol.navigation.core.NavHost

@Composable
fun MainNavigation() {
    NavHost(
        startDestination = MainGraph.AuthModule
    ) {
        authNavigation()
        gestorDashboardNavigation()
        driverDashboardNavigation()
    }
}