package com.repsol.core_ui.stateful

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.repsol.core_ui.stateful.interactor.effect.LocalUiEffectProvider
import com.repsol.core_ui.stateful.interactor.state.LocalUiStateProvider
import com.repsol.core_ui.stateful.loader.LoaderEventConnector

/**
 * Composición local para almacenar el StatefulScope.
 * Lanza un error si no se proporciona un StatefulScope.
 */
internal val LocalStatefulScope = staticCompositionLocalOf<StatefulScope<*>> {
    error("No StatefulScope provided. Use Stateful<Handler>")
}

/**
 * Obtiene el StatefulScope actual del contexto de composición.
 *
 * @return El StatefulScope actual.
 */
@Suppress("UNCHECKED_CAST")
@Composable
fun <H: Any> localStateful(): StatefulScope<H> {
    return LocalStatefulScope.current as StatefulScope<H>
}

/**
 * Proveedor de componentes stateful para el contexto de composición.
 *
 * @param handlerFactory Función composable que proporciona el manejador.
 * @param content Contenido composable que puede acceder a los componentes stateful proporcionados.
 */
@Composable
internal fun <H: Any> StatefulComponentsProvider(
    handlerFactory: @Composable () -> H,
    content: @Composable () -> Unit
) {

    val scope = createStatefulScope(
        handlerFactory = handlerFactory
    ).apply {
        LoaderEventConnector()
    }
    CompositionLocalProvider(
        LocalStatefulScope provides scope
    ) {
        LocalUiStateProvider(
            handler = scope.handler
        ) {
            LocalUiEffectProvider {
                content()
            }
        }
    }
}