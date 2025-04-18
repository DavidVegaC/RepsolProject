package com.repsol.tools.utils.number

import android.icu.text.NumberFormat

interface NumberParser<N: Number> {

    val numberFormat: NumberFormat
    val defaultAmount: N

    fun parse(amountText: CharSequence): N
}