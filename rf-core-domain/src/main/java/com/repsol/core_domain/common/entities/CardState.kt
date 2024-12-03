package com.repsol.core_domain.common.entities

enum class CardState(val code: String, val description: String) {
    ACTIVE("0", "Activas"),
    INACTIVE("1", "Inactivas"),
    CANCELLED("2", "Anuladas");

    companion object {

        fun fromCode(code: String): CardState? {
            return entries.find { it.code == code }
        }
    }
}