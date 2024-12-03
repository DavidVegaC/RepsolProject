package com.repsol.core_domain.common.entities

enum class CardFeature(val code: String, val description: String) {
    VEHICLE("2", "Vehículo"),
    DRIVER("4", "Conductor"),
    POOL("6", "Pool");

    companion object {
        fun fromCode(code: String): CardFeature? {
            return entries.find { it.code == code }
        }

        fun isDriver(code: String): Boolean = DRIVER.code.contentEquals(code)
    }
}