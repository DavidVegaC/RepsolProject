package com.repsol.tools.utils

import android.icu.text.NumberFormat
import com.repsol.core_domain.common.entities.Currency
import com.repsol.tools.utils.number.DoubleParser
import java.util.Locale

object CurrencyFormatter {

    private val soles = Locale("es", "PE")
    private val dollars = Locale("en", "US")

    private val doubleParser: DoubleParser
        get() {
            val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
            return DoubleParser(numberFormat = numberFormat)
        }

    fun formatCurrency(amount: String, currency: Currency): String {
        val amountAsDouble: Double = doubleParser.parse(amount)
        return "${currency.symbol} $amountAsDouble"
    }


    private fun formatCurrency(amount: Double, locale: Locale): String {
        val format = java.text.NumberFormat.getCurrencyInstance(locale)
        format.currency = java.util.Currency.getInstance(locale)
        return format.format(amount)
    }

    fun formatCurrencyInSoles(amount: Double): String {
        return formatCurrency(amount, soles)
    }

    fun formatCurrencyInDollars(amount: Double): String {
        return formatCurrency(amount, dollars)
    }
}