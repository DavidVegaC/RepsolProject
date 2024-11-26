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
    CANCELED(
        id = "2",
        displayText = "canceled",
    );

    companion object {

        fun identifyBy(id: String): Status? = when(id) {
            ACTIVE.id -> ACTIVE
            INACTIVE.id -> INACTIVE
            CANCELED.id -> CANCELED
            else -> null
        }
    }
}