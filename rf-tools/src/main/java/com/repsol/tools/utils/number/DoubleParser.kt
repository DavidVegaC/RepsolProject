package com.repsol.tools.utils.number

import android.icu.text.NumberFormat
import com.repsol.tools.utils.parseOrNull

class DoubleParser(
    override val numberFormat: NumberFormat,
    override val defaultAmount: Double = DEFAULT_ZERO_AMOUNT,
): NumberParser<Double> {

    override fun parse(amountText: CharSequence): Double {
        val number: Number? = numberFormat.parseOrNull(amountText.toString())
        return number?.toDouble() ?: defaultAmount
    }

    companion object {

        private val DEFAULT_ZERO_AMOUNT
            get() = 0.0
    }
}