package com.repsol.core_domain.common.entities

enum class UserProfile(val id: Int) {

    GESTOR(3),
    DRIVER(28),
    NONE(0);

    companion object {

        fun findById(value: Int): UserProfile {
            return entries.firstOrNull { it.id == value } ?: NONE
        }

        fun isGestor(id: Int): Boolean {
            return GESTOR.id == id
        }

        fun isDriver(id: Int): Boolean {
            return DRIVER.id == id
        }

        fun isValid(id: Int): Boolean {
            return GESTOR.id == id || DRIVER.id == id
        }
    }
}