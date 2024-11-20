package com.repsol.tools.utils

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object CurrencyFormatter {

    private val soles = Locale("es", "PE")
    private val dollars = Locale("en", "US")

    private fun formatCurrency(amount: Double, locale: Locale): String {
        val format = NumberFormat.getCurrencyInstance(locale)
        format.currency = Currency.getInstance(locale)
        return format.format(amount)
    }

    fun formatCurrencyInSoles(amount: Double): String {
        return formatCurrency(amount, soles)
    }

    fun formatCurrencyInDollars(amount: Double): String {
        return formatCurrency(amount, dollars)
    }
}