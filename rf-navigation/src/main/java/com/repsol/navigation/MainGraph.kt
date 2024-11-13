package com.repsol.navigation

import kotlinx.serialization.Serializable

object MainGraph {

    @Serializable
    object AuthModule

    @Serializable
    object GestorDashboardModule

    @Serializable
    object DriverDashboardModule

    @Serializable
    object HomeModule
}