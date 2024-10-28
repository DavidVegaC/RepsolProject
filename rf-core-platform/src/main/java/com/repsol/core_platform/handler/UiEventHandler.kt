package com.repsol.core_platform.handler

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Interfaz marcadora para los eventos de la UI.
 */
interface UiEvent

/**
 * Interfaz que define cómo se manejan los eventos de la UI.
 *
 * @param E Tipo que representa los eventos de la UI. Debe implementar [UiEvent].
 */
interface UiEventHandler<E: UiEvent> {
    /**
     * Envía un evento.
     */
    fun E.send()

    /**
     * Envía un evento dado.
     *
     * @param event El evento a enviar.
     */
    fun sendUiEvent(event: E)
    suspend fun setOnUiEvent(handle: suspend (E) -> Unit)
}

/**
 * Implementación por defecto del [UiEventHandler].
 *
 * @param E Tipo que representa los eventos de la UI. Debe implementar [UiEvent].
 */
private class DefaultEventHandler<E: UiEvent>: UiEventHandler<E> {

    private val _event = Channel<E>(BUFFERED)

    override fun sendUiEvent(event: E) {
        _event.trySend(event)
    }

    override fun E.send() {
        sendUiEvent(this)
    }

    override suspend fun setOnUiEvent(handle: suspend (E) -> Unit) {
        _event.receiveAsFlow().collectLatest {
            handle(it)
        }
    }

}

/**
 * Crea una instancia de [UiEventHandler].
 *
 * @param E Tipo que representa los eventos de la UI. Debe implementar [UiEvent].
 * @return Una instancia de [UiEventHandler].
 */
fun <E: UiEvent> uiEventHandler(): UiEventHandler<E> {
    return DefaultEventHandler()
}