package com.repsol.gestor_dashboard.domain.entity

import com.repsol.core_domain.common.error.GlobalError

data class CreditBalance(
    val balance: String,
    val lineCred: String,
    val paymentDeadLine: String,
    val excedido: String,
    val fechaCaducidad: String,
    val recargar: Boolean,
    val importe: String,
    val deuda: String,
    val errorManager: GlobalError
)