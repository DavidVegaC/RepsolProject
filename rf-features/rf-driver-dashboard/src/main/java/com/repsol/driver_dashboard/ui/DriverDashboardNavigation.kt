package com.repsol.driver_dashboard.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.repsol.driver_dashboard.ui.dashboard.DriverDashboardScreen
import com.repsol.driver_dashboard.ui.dashboard.MyQrScreen
import com.repsol.driver_dashboard.ui.dashboard.TrackingScreen
import com.repsol.driver_dashboard.ui.index.IndexDriverScreen
import com.repsol.navigation.MainGraph

fun NavGraphBuilder.driverDashboardNavigation() {
    navigation<MainGraph.DriverDashboardModule>(
        startDestination = DriverDashboardGraph.Dashboard
    ) {
        composable<DriverDashboardGraph.Dashboard> {
            DriverDashboardScreen()
        }
    }
}

fun NavGraphBuilder.contentDashboardNavigation() {
    navigation<MainGraph.GestorDashboardModule>(
        startDestination = DriverDashboardGraph.Index
    ) {
        composable<DriverDashboardGraph.Index> {

        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) { // cada composable seria un submodulo
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { IndexDriverScreen(Modifier.padding(paddingValues)) }
        composable("my_qr") { MyQrScreen(Modifier.padding(paddingValues)) }
        composable("tracking") { TrackingScreen(Modifier.padding(paddingValues)) }
    }
}