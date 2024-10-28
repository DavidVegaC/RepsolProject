package com.repsol.core_platform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.repsol.core_platform.handler.LoaderHandler
import com.repsol.core_platform.handler.UiEffect
import com.repsol.core_platform.handler.UiEffectHandler
import com.repsol.core_platform.handler.UiEvent
import com.repsol.core_platform.handler.UiEventHandler
import com.repsol.core_platform.handler.UiIntent
import com.repsol.core_platform.handler.UiIntentHandler
import com.repsol.core_platform.handler.UiState
import com.repsol.core_platform.handler.UiStateHandler
import com.repsol.core_platform.handler.loaderHandler
import com.repsol.core_platform.handler.uiEffectHandler
import com.repsol.core_platform.handler.uiEventHandler
import com.repsol.core_platform.handler.uiIntentHandler
import com.repsol.core_platform.handler.uiStateHandler

/**
 * Clase abstracta base para los ViewModels en la aplicación con MVI.
 *
 * @param S El tipo del estado de la UI.
 * @param I El tipo del intento de la UI.
 * @param E El tipo del evento hacia la UI.
 * @param savedStateHandle El SavedStateHandle para el ViewModel.
 * @param defaultUiState Una función lambda para proporcionar el estado de UI predeterminado.
 */
abstract class CoreViewModel<S : UiState, I : UiIntent, E : UiEvent>(
    savedStateHandle: SavedStateHandle,
    defaultUiState: SavedStateHandle.() -> S
) : ViewModel(),
    UiStateHandler<S> by uiStateHandler(savedStateHandle, defaultUiState),
    UiIntentHandler<I> by uiIntentHandler(),
    UiEventHandler<E> by uiEventHandler(),
    LoaderHandler by loaderHandler(),
    UiEffectHandler by uiEffectHandler() {

    init {
        // Inicializa el ViewModel con el scope del ViewModel y la función handleIntent
        this.initialize(viewModelScope, ::handleIntent)
        viewModelScope.launch(Dispatchers.Main) {
            onInit()
        }
    }

    open suspend fun onInit() {}

    /**
     * Función abstracta que debe ser implementada para manejar los intentos de la UI.
     *
     * @param intent El intento de la UI que debe ser manejado.
     */
    protected abstract suspend fun handleIntent(intent: I)

    /**
     * Habilita un efecto de UI de la clase especificada.
     *
     * @param timeMillis La duración en milisegundos durante la cual el efecto de UI debe estar habilitado. Si es null, el efecto se habilita indefinidamente.
     */
    inline fun <reified E: UiEffect> E.enable(timeMillis: Long? = null) {
        if (timeMillis == null) {
            enableUiEffect(E::class, this)
        } else {
            enableUiEffect(E::class, this, timeMillis = timeMillis)
        }
    }

    /**
     * Deshabilita un efecto de UI de la clase especificada.
     */
    inline fun <reified E: UiEffect> E.disable() {
        disableUiEffect(E::class)
    }

    /**
     * Actualiza el estado de la UI utilizando una función de reducción.
     *
     * @param reduce Función que toma el estado actual y devuelve el nuevo estado.
     */
    inline fun <S: UiState> UiStateHandler<S>.setUiState(reduce: S.() -> S) {
        setUiState(reduce(uiState))
    }
}