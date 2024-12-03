package com.repsol.tools.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String?.toDoubleOrDefault(default: Double = ZERO_DOUBLE): Double {
    return this?.toDoubleOrNull() ?:default
}

fun String?.toIntOrDefault(default: Int = ZERO): Int {
    return this?.toIntOrNull() ?:default
}

fun String.toNumericValue(): Double {
    return this.replace("[^\\d.]".toRegex(), "")
        .toDoubleOrDefault()
}

val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es"))

fun convertDateToStringFormat(date: Date?): String {
    return date?.let(dateFormat::format) ?: EMPTY_STRING
}

