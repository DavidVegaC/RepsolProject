package com.repsol.core_platform.handler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Interfaz marcadora para las intenciones del usuario.
 */
interface UiIntent

/**
 * Interfaz que define cómo se manejan las intenciones del usuario.
 *
 * @param I Tipo que representa las intenciones del usuario. Debe implementar [UiIntent].
 */
interface UiIntentHandler<I: UiIntent> {
    /**
     * Ejecuta una intención.
     */
    fun I.exec()

    /**
     * Ejecuta una intención dada.
     *
     * @param intent La intención a ejecutar.
     */
    fun execUiIntent(intent: I)

    /**
     * Inicializa el manejador de intenciones.
     *
     * @param scope El CoroutineScope en el que se ejecutarán las intenciones.
     * @param handle Función que maneja cada intención.
     */
    fun initialize(scope: CoroutineScope, handle: suspend (intent: I) -> Unit)
}

/**
 * Implementación por defecto del [UiIntentHandler].
 *
 * @param I Tipo que representa las intenciones del usuario. Debe implementar [UiIntent].
 */
private class DefaultIntentHandler<I: UiIntent>: UiIntentHandler<I> {

    private val _intent = Channel<I>(BUFFERED)

    override fun execUiIntent(intent: I) {
        _intent.trySend(intent)
    }

    override fun I.exec() {
        execUiIntent(this)
    }

    override fun initialize(scope: CoroutineScope, handle: suspend (intent: I) -> Unit) {
        scope.launch(Dispatchers.IO) {
            _intent.receiveAsFlow().collectLatest { intent ->
                scope.launch(Dispatchers.Main) {
                    handle(intent)
                }
            }
        }
    }
}

/**
 * Crea una instancia de [UiIntentHandler].
 *
 * @param I Tipo que representa las intenciones del usuario. Debe implementar [UiIntent].
 * @return Una instancia de [UiIntentHandler].
 */
fun <I: UiIntent> uiIntentHandler(): UiIntentHandler<I> {
    return DefaultIntentHandler()
}