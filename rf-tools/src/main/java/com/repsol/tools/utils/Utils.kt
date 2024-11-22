package com.repsol.tools.utils

fun String?.toDoubleOrDefault(default: Double = ZERO_DOUBLE): Double {
    return this?.toDoubleOrNull() ?:default
}

fun String?.toIntOrDefault(default: Int = ZERO): Int {
    return this?.toIntOrNull() ?:default
}

fun String.toNumericValue(): Double? {
    return this.replace("[^\\d.]".toRegex(), "")
        .toDoubleOrDefault()
}