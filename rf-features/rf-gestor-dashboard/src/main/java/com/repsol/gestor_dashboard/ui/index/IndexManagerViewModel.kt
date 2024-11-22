package com.repsol.gestor_dashboard.ui.index


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.repsol.core_domain.storage.SessionStorage
import com.repsol.core_platform.CoreViewModel
import com.repsol.gestor_dashboard.domain.entity.CreditBalance
import com.repsol.gestor_dashboard.domain.result.GetCreditBalanceResult
import com.repsol.gestor_dashboard.domain.usecase.GetCreditBalanceUseCase
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiIntent
import com.repsol.tools.utils.CurrencyFormatter
import com.repsol.tools.utils.UserSession
import com.repsol.tools.utils.ZERO
import com.repsol.tools.utils.toDoubleOrDefault
import com.repsol.tools.utils.toNumericValue
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiState as UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndexManagerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCreditBalanceUseCase: GetCreditBalanceUseCase
) : CoreViewModel<UiState, UiIntent, UiEvent>(
    savedStateHandle = savedStateHandle,
    defaultUiState = {
        UiState()
    }
) {

    init {
        loadCreditBalance()

    }

    override suspend fun handleIntent(intent: UiIntent) {
        when (intent) {
            IndexUiIntent.onRetryClick -> retryLoadingData()
        }

    }

    private fun retryLoadingData() {
        setUiState { copy(isLoading = true, errorMessage = "", showRetry = false) }
        loadCreditBalance()
    }

    private fun loadCreditBalance() {
        setUiState { copy(isLoading = true, showRetry = false) }
        viewModelScope.launch {
            val result = getCreditBalanceUseCase(
                params = SessionStorage.getString(UserSession.ID_CLIENT).orEmpty()
            )
            handleResult(result)
            setUiState { copy(isLoading = false) }
        }
    }


    private fun handleResult(result: GetCreditBalanceResult) {
        when (result) {
            is GetCreditBalanceResult.Error -> {
                setUiState {
                    copy(
                        errorMessage = result.message,
                        showRetry = true,
                        isLoading = false,
                        data = null,
                        overdueDebt = false
                    )
                }
            }

            is GetCreditBalanceResult.Success -> {
                setUiState {
                    copy(
                        data = result.data,
                        errorMessage = "",
                        showRetry = false,
                        isLoading = false,
                        commercialGoal = calculateCommercialGoal(result.data),
                        overdueDebt = overdueDebt(result.data)
                    )
                }
            }
        }
    }

    private fun overdueDebt(data: CreditBalance?): Boolean {
        val debt = data?.deuda?.toNumericValue()

        return (CurrencyFormatter.formatCurrencyInSoles(debt!!).contentEquals("")
                || CurrencyFormatter.formatCurrencyInSoles(debt).contentEquals(ZERO.toString()))
    }

    private fun calculateCommercialGoal(data: CreditBalance?): Int {

        val lineCred = data?.lineCred.toDoubleOrDefault()
        val availableBalance = data?.balance.toDoubleOrDefault()

        return if (lineCred > ZERO) {
            ((lineCred - availableBalance) / lineCred * 100).toInt()
        } else {
            ZERO
        }
    }


}