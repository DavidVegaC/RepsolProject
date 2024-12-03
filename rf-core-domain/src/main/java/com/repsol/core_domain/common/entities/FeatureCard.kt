package com.repsol.core_domain.common.entities

enum class FeatureCard(
    val id: String,
    val displayText: String,
) {

    VEHICLE(
        id = "2",
        displayText = "Vehículo"
    ),
    DRIVER(
        id = "4",
        displayText = "Conductor"
    ),
    POOL(
        id = "6",
        displayText = "Pool"
    );

    companion object {

        fun identifyBy(id: String): FeatureCard? = when(id) {
            VEHICLE.id -> VEHICLE
            DRIVER.id -> DRIVER
            POOL.id -> POOL
            else -> null
        }

        fun isVehicle(id: String): Boolean = VEHICLE.id == id

        fun isDriver(id: String): Boolean = DRIVER.id == id
    }
}