package com.repsol.core_data.common.remote

sealed class DataError {
    data class Default(val message: String): DataError()
}