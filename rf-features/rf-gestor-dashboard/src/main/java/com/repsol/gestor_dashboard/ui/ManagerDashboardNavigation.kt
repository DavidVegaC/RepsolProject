package com.repsol.gestor_dashboard.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.repsol.gestor_dashboard.ui.home.DashboardManagerScreen
import com.repsol.gestor_dashboard.ui.index.IndexManagerScreen
import com.repsol.gestor_dashboard.ui.vehicle.VehicleScreen
import com.repsol.navigation.MainGraph

fun NavGraphBuilder.gestorDashboardNavigation() {
    navigation<MainGraph.GestorDashboardModule>(
        startDestination = ManagerDashboardGraph.Dashboard
    ) {
        composable<ManagerDashboardGraph.Dashboard> {
            DashboardManagerScreen()
        }
    }
}

fun NavGraphBuilder.homeDashboardNavigation(paddingValues: PaddingValues) {
    navigation<MainGraph.GestorDashboardModule>(
        startDestination = ManagerDashboardGraph.Index
    ) {
        composable<ManagerDashboardGraph.Index> {
            IndexManagerScreen(Modifier.padding(paddingValues))
        }
        composable<ManagerDashboardGraph.Vehicles> {
            VehicleScreen(Modifier.padding(paddingValues))
        }
    }
}