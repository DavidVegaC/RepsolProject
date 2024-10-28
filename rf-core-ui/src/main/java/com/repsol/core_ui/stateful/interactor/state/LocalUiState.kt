package com.repsol.core_ui.stateful.interactor.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import com.repsol.core_platform.handler.UiState
import com.repsol.core_platform.handler.UiStateHandler

/**
 * Composición local para almacenar el estado de la UI.
 * Lanza una excepción si no se proporciona un estado de UI.
 */
internal val LocalUiState = staticCompositionLocalOf<State<UiState>> {
    throw IllegalStateException("No UiState provided")
}

/**
 * Proveedor de estado de UI local para el contexto de composición.
 *
 * @param handler Manejador que proporciona el flujo de estado de UI.
 * @param content Contenido composable que puede acceder al estado de UI proporcionado.
 */
@Composable
internal fun LocalUiStateProvider(
    handler: Any,
    content: @Composable () -> Unit
) {
    if (handler is UiStateHandler<*>) {
        CompositionLocalProvider(
            LocalUiState provides handler.uiStateFlow.collectAsState(handler.uiState)
        ) {
            content()
        }
    } else {
        content()
    }
}