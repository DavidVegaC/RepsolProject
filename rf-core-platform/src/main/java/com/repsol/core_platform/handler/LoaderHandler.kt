package com.repsol.core_platform.handler

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest

/**
 * Interfaz que define la capacidad de un componente para manejar eventos de carga (loading).
 *
 * Esta interfaz permite a un componente notificar y escuchar cambios en su estado de carga.
 */
interface LoaderHandler {

    /**
     * Propiedad que indica si el componente está en estado de carga (loading) o no.
     */
    var isLoading: Boolean

    /**
     * Función suspendida que permite a los consumidores de la interfaz registrarse para recibir notificaciones
     * cuando el estado de carga (loading) del componente cambia.
     *
     * @param listener El oyente que será notificado cuando el estado de carga cambie. Recibe un valor booleano
     * que indica si el componente está en estado de carga (true) o no (false).
     */
    suspend fun onLoading(listener: (isLoading: Boolean) -> Unit)

}

class DefaultLoaderHandler: LoaderHandler {

    private val _loader = MutableStateFlow(false)

    override var isLoading: Boolean
        get() = _loader.value
        set(value) {
            _loader.tryEmit(value)
        }

    override suspend fun onLoading(listener: (isLoading: Boolean) -> Unit) {
        _loader.collectLatest {
            listener(it)
        }
    }
}

/**
 * Función que crea y devuelve una instancia de DefaultLoaderHandler.
 *
 * @return Una instancia de LoaderHandler.
 */
fun loaderHandler(): LoaderHandler {
    return DefaultLoaderHandler()
}