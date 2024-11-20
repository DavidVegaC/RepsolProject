package com.repsol.gestor_dashboard.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.repsol.gestor_dashboard.ui.dashboard.ConductoresScreen
import com.repsol.gestor_dashboard.ui.dashboard.ConfiguracionesScreen
import com.repsol.gestor_dashboard.ui.dashboard.GestorDashboardScreen
import com.repsol.gestor_dashboard.ui.dashboard.MediosPagoScreen
import com.repsol.gestor_dashboard.ui.dashboard.ReportesScreen
import com.repsol.gestor_dashboard.ui.dashboard.TarjetasScreen
import com.repsol.gestor_dashboard.ui.dashboard.TrackingScreen
import com.repsol.gestor_dashboard.ui.detailvehicle.DetailVehicleScreen
import com.repsol.gestor_dashboard.ui.index.IndexManagerScreen
import com.repsol.gestor_dashboard.ui.vehicle.VehicleScreen
import com.repsol.navigation.MainGraph

fun NavGraphBuilder.gestorDashboardNavigation() {
    navigation<MainGraph.GestorDashboardModule>(
        startDestination = GestorDashboardGraph.Dashboard
    ) {
        composable<GestorDashboardGraph.Dashboard> {
            GestorDashboardScreen()
        }
    }
}

fun NavGraphBuilder.contentDashboardNavigation() {
    navigation<MainGraph.GestorDashboardModule>(
        startDestination = GestorDashboardGraph.Index
    ) {
        composable<GestorDashboardGraph.Index> {
            IndexManagerScreen()
        }
        composable<GestorDashboardGraph.Vehicles> {
            VehicleScreen()
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) { // cada composable seria un submodulo
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { IndexManagerScreen(Modifier.padding(paddingValues)) }
        composable("vehiculos") { VehicleScreen(Modifier.padding(paddingValues)) }
        composable("tarjetas") { TarjetasScreen(Modifier.padding(paddingValues)) }
        composable("conductores") { ConductoresScreen(Modifier.padding(paddingValues)) }
        composable("tracking") { TrackingScreen(Modifier.padding(paddingValues)) }
        composable("reportes") { ReportesScreen(Modifier.padding(paddingValues)) }
        composable("medios_pago") { MediosPagoScreen(Modifier.padding(paddingValues)) }
        composable("configuracion") { ConfiguracionesScreen(Modifier.padding(paddingValues)) }
    }
}