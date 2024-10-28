package com.repsol.core_platform.handler

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Interfaz marcadora para los estados de UI.
 */
interface UiState

/**
 * Interfaz que define cómo se maneja el estado de la UI.
 *
 * @param S Tipo que representa el estado de la UI. Debe implementar [UiState].
 */
interface UiStateHandler<S: UiState> {
    /**
     * Flow que emite el estado actual de la UI.
     */
    val uiStateFlow: Flow<S>

    /**
     * El estado actual de la UI.
     */
    val uiState: S

    fun setUiState(uiState: S)
}

/**
 * Implementación por defecto del [UiStateHandler].
 *
 * @param S Tipo que representa el estado de la UI. Debe implementar [UiState].
 * @property savedStateHandle Instancia de SavedStateHandle para manejar el estado guardado.
 * @property defaultUiState Función que proporciona el estado inicial de la UI.
 */
private class DefaultUiStateHandler<S: UiState>(
    savedStateHandle: SavedStateHandle,
    defaultUiState: SavedStateHandle.() -> S
): UiStateHandler<S> {

    private val _state = MutableStateFlow(defaultUiState(savedStateHandle))
    override val uiStateFlow get() = _state
    override val uiState get() = _state.value

    /**
     * Actualiza el estado de la UI de manera sincronizada.
     *
     * @param uiState El nuevo estado de la UI.
     */
    @Synchronized
    override fun setUiState(uiState: S) {
        _state.tryEmit(uiState)
    }
}

/**
 * Crea una instancia de [UiStateHandler].
 *
 * @param S Tipo que representa el estado de la UI. Debe implementar [UiState].
 * @param savedStateHandle Instancia de SavedStateHandle para manejar el estado guardado.
 * @param defaultUiState Función que proporciona el estado inicial de la UI.
 * @return Una instancia de [UiStateHandler].
 */
fun <S: UiState> uiStateHandler(savedStateHandle: SavedStateHandle, defaultUiState: SavedStateHandle.() -> S): UiStateHandler<S> {
    return DefaultUiStateHandler(savedStateHandle, defaultUiState)
}