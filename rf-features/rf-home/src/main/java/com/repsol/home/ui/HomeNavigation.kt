package com.repsol.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.repsol.home.ui.home.DashboardManagerScreen
import com.repsol.home.ui.home.HomeManagerScreen
import com.repsol.navigation.MainGraph

fun NavGraphBuilder.homeNavigation() {
    navigation<MainGraph.HomeModule>(
        startDestination = HomeGraph.Home
    ) {
        composable<HomeGraph.Home> {
            DashboardManagerScreen()
        }
    }
}