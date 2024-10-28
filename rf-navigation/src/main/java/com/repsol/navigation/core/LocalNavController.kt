package com.repsol.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost as NavHostCompose

private val LocalNavController = staticCompositionLocalOf<NavController?> {
    null
}

@Composable
fun localNavController(): NavController {
    return LocalNavController.current ?: rememberNavController()
}

@Composable
fun NavHost(
    startDestination: Any,
    builder: NavGraphBuilder.() -> Unit
) {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHostCompose(
            navController = navController,
            startDestination = startDestination,
            builder = builder
        )
    }
}