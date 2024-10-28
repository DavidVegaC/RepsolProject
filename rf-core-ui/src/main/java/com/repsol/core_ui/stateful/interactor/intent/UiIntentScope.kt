package com.repsol.core_ui.stateful.interactor.intent

import com.repsol.core_platform.handler.UiEffect
import com.repsol.core_platform.handler.UiEffectHandler
import com.repsol.core_platform.handler.UiState
import com.repsol.core_platform.handler.UiStateHandler

/**
 * Alcance de intención de UI que maneja el estado y los efectos de UI.
 *
 * @param uiStateHandler Manejador de estado de UI.
 * @param uiEffectHandler Manejador de efectos de UI.
 */
class UiIntentScope<S: UiState> internal constructor(
    uiStateHandler: UiStateHandler<S>,
    uiEffectHandler: UiEffectHandler,
): UiStateHandler<S> by uiStateHandler, UiEffectHandler by uiEffectHandler {

    /**
     * Establece el estado de UI.
     *
     * @param reduce Función que reduce el estado de UI.
     */
    fun <S: UiState> UiIntentScope<S>.setUiState(reduce: S.() -> S) {
        setUiState(uiState.reduce())
    }

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
}