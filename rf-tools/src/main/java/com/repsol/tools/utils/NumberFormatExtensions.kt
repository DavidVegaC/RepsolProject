package com.repsol.tools.utils

import android.icu.text.NumberFormat

fun NumberFormat.parseOrNull(source: String): Number? = try {
    parse(source)
} catch (e: Exception) {
    null
}