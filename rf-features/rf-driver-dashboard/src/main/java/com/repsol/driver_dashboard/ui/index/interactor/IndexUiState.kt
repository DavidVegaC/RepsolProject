package com.repsol.driver_dashboard.ui.index.interactor

import com.repsol.core_platform.handler.UiState

data class IndexUiState(
    val driverName: String,
    val businessName: String,
    val cardDriver: String = "",
    val cardNumber: String = "",
    val cardType: String = "",
    val controlTypeDays: String = "",
    val stopsLimitAmount: String = "",
    val balanceAmount:String = "",
    val activationDate: String = "",
    val costCenter: String = "",
): UiState