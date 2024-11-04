package com.repsol.core_domain.common.error

class GlobalError(
    val code: String? = null,
    val message: String? = null,
    val cause: Throwable? = null,
)