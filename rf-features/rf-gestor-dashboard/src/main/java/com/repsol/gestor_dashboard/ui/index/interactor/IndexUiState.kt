package com.repsol.gestor_dashboard.ui.index.interactor

import com.repsol.core_platform.handler.UiState
import com.repsol.gestor_dashboard.domain.entity.CreditBalance
import com.repsol.tools.utils.ZERO

data class IndexUiState(
    val data: CreditBalance? = null,
    val commercialGoal: Int? = ZERO,
    val errorMessage: String? = null,
    val overdueDebt: Boolean = false,
    val showRetry: Boolean = false,
    val isLoading: Boolean = false
): UiState