package com.repsol.gestor_dashboard.ui.index

import android.content.Context

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_domain.common.entities.MimeType
import androidx.lifecycle.viewModelScope
import com.repsol.core_platform.CoreViewModel
import com.repsol.gestor_dashboard.domain.result.PostDownloadResult
import com.repsol.gestor_dashboard.domain.usecase.PostDownloadUseCase
import com.repsol.tools.utils.DownloadUtils.saveBase64FileToDownload
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiEffect as UiEffect
import com.repsol.gestor_dashboard.domain.entity.CreditBalance
import com.repsol.gestor_dashboard.domain.result.GetCreditBalanceResult
import com.repsol.gestor_dashboard.domain.usecase.GetCreditBalanceUseCase
import com.repsol.tools.utils.Formatters
import com.repsol.tools.utils.ZERO
import com.repsol.tools.utils.toDoubleOrDefault
import com.repsol.tools.utils.toNumericValue
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiState as UiState
import kotlinx.coroutines.launch

@HiltViewModel
class IndexManagerViewModel @Inject constructor(
    private val postDownloadUseCase: PostDownloadUseCase,
    private val getCreditBalanceUseCase: GetCreditBalanceUseCase,
    savedStateHandle: SavedStateHandle,
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
            is UiIntent.OnDownloadAllPrices-> downloadAllPrices(intent.context, intent.isApi29OrHigher)
            UiIntent.OnRetryClick -> retryLoadingData()
        }
    }

    private suspend fun downloadAllPrices(context: Context, isApi29OrHigher: Boolean) {
        isLoading = true
        when (val result = postDownloadUseCase()) {
            is PostDownloadResult.Success -> {
                val isSavedFiled: Boolean = saveBase64FileToDownload(
                    context = context,
                    base64String = result.base64String,
                    fileName = "Reportes Precios.xlsx",
                    mimetype = MimeType.EXCEL.value,
                    isApi29OrHigher = isApi29OrHigher,
                )

                if (isSavedFiled) {
                    UiEffect.SuccessDownloadSnackbar(text = "Se guardó exitosamente el archivo en Descargas.").enable(6000)
                } else {
                    UiEffect.ErrorDownloadSnackbar(text = "Ocurrió un error al guardar el archivo.").enable(6000)
                }
            }
            is PostDownloadResult.Error -> {
                UiEffect.ErrorDownloadSnackbar(text = "Error en el servicio").enable(5000)
            }
        }
        isLoading = false
    }

    private fun retryLoadingData() {
        setUiState { copy(isLoading = true, errorMessage = "", showRetry = false) }
        loadCreditBalance()
    }

    private fun loadCreditBalance() {
        setUiState { copy(isLoading = true, showRetry = false) }
        viewModelScope.launch {
            val result = getCreditBalanceUseCase()
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
        val debt = data?.debtAmount?.toNumericValue()

        return (Formatters.formatCurrencyInSoles(debt!!).contentEquals("")
                || Formatters.formatCurrencyInSoles(debt).contentEquals(ZERO.toString()))
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