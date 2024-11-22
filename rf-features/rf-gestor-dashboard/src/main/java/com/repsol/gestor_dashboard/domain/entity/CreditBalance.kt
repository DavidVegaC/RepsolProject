package com.repsol.gestor_dashboard.domain.entity

import com.repsol.core_domain.common.error.GlobalError

data class CreditBalance(
    val balance: String,
    val lineCred: String,
    val paymentDeadLine: String,
    val exceeded: String,
    val expirationDate: String,
    val shouldReload: Boolean,
    val amount: String,
    val debtAmount: String,
    val errorManager: GlobalError
)