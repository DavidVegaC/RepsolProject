package com.repsol.core_domain.common.entities

enum class Status(
    val id: String,
    val displayText: String,
) {

    ACTIVE(
        id = "0",
        displayText = "active"
    ),
    INACTIVE(
        id = "1",
        displayText = "inactive"
    ),
    NONE(
        id = "NONE",
        displayText = "",
    );

    companion object {

        fun identifyBy(id: String): Status = when(id) {
            ACTIVE.id -> ACTIVE
            INACTIVE.id -> INACTIVE
            else -> NONE
        }
    }
}