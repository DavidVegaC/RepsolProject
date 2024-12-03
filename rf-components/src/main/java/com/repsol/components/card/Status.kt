package com.repsol.components.card

import com.repsol.components.style.RFColor

enum class Status(val id: String, val rfColor: RFColor) {
    ACTIVE("0", RFColor.UxComponentColorGreen),
    INACTIVE("1",RFColor.UxComponentColorRed ),
    CANCELED("2", RFColor.UxComponentColorGainsboro),
    NONE("-1", RFColor.UxComponentColorWhite);

    companion object {
        fun findById(id: String): Status = when(id.uppercase()) {
            ACTIVE.id -> ACTIVE
            INACTIVE.id -> INACTIVE
            CANCELED.id -> CANCELED
            else -> NONE
        }
    }
}