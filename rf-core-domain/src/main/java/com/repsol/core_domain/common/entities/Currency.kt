package com.repsol.core_domain.common.entities

enum class Currency(
    val id: String,
    val displayText: String,
    val symbol: String,
) {

    PEN(
        id = "PEN",
        displayText = "Soles",
        symbol = "S/",
    ),
    USD(
        id = "USD",
        displayText = "Dólares",
        symbol = "US$",
    ),
    EUR(
        id = "EUR",
        displayText = "Euros",
        symbol = "€",
    ),
    NONE(
        id = "NONE",
        displayText = "",
        symbol = "",
    );

    companion object {

        fun identifyBy(id: String): Currency = when(id.uppercase()) {
            PEN.id -> PEN
            USD.id -> USD
            EUR.id -> EUR
            else -> NONE
        }
    }
}