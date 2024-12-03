package com.repsol.gestor_dashboard.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.repsol.gestor_dashboard.ui.cards.detail.DetailCardScreen
import com.repsol.gestor_dashboard.ui.cards.home.filter.FilterCardsScreen
import com.repsol.gestor_dashboard.ui.cards.home.CardsScreen
import com.repsol.gestor_dashboard.ui.cards.home.CardsViewModel
import com.repsol.gestor_dashboard.ui.dashboard.ConductoresScreen
import com.repsol.gestor_dashboard.ui.dashboard.ConfiguracionesScreen
import com.repsol.gestor_dashboard.ui.dashboard.GestorDashboardScreen
import com.repsol.gestor_dashboard.ui.dashboard.MediosPagoScreen
import com.repsol.gestor_dashboard.ui.dashboard.ReportesScreen
import com.repsol.gestor_dashboard.ui.dashboard.TrackingScreen
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

    val viewModel: CardsViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { IndexManagerScreen(Modifier.padding(paddingValues)) }
        composable("vehiculos") { VehicleScreen(Modifier.padding(paddingValues)) }
        composable("cards") { CardsScreen(navController, Modifier.padding(paddingValues), viewModel) }
        composable(
            route = "cards_detail/{itemCard}",
            arguments = listOf(navArgument("itemCard") {
                type = NavType.StringType
            })
        ) {
            DetailCardScreen(navController, Modifier.padding(bottom = paddingValues.calculateBottomPadding()))
        }
        dialog(route = "filter_cards", dialogProperties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
        )) {
            FilterCardsScreen(navController, viewModel)
        }
        composable("conductores") { ConductoresScreen(Modifier.padding(paddingValues)) }
        composable("tracking") { TrackingScreen(Modifier.padding(paddingValues)) }
        composable("reportes") { ReportesScreen(Modifier.padding(paddingValues)) }
        composable("medios_pago") { MediosPagoScreen(Modifier.padding(paddingValues)) }
        composable("configuracion") { ConfiguracionesScreen(Modifier.padding(paddingValues)) }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBefore("/")
}