package com.repsol.core_ui.stateful

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.repsol.core_platform.handler.UiState
import com.repsol.core_ui.stateful.interactor.effect.RegisterGlobalUiEffects
import com.repsol.core_ui.stateful.interactor.effect.RegisterLocalUiEffects
import com.repsol.core_ui.stateful.interactor.effect.uiEffectPreviewProvider
import com.repsol.core_ui.stateful.interactor.intent.uiIntentPreviewProvider
import com.repsol.core_ui.stateful.interactor.state.defaultUiStatePreview

/**
 * Crea un `StatefulScope` utilizando un `handlerFactory` composable.
 *
 * @param H Tipo del manejador.
 * @param handlerFactory Función composable que proporciona el manejador.
 * @return Una instancia de `StatefulScope`.
 */
@Composable
fun <H: Any> createStatefulScope(
    handlerFactory: @Composable () -> H
): StatefulScope<H> {
    return if (isPreview()) {
        val defaultUiStatePreview = defaultUiStatePreview<UiState>()
        val uiEffectPreviewProvider = uiEffectPreviewProvider()
        val uiIntentPreviewProvider = uiIntentPreviewProvider()
        val coroutineScope = rememberCoroutineScope()
        remember {
            StatefulScope(
                handler = PreviewHandler(
                    defaultUiState = defaultUiStatePreview,
                    uiEffectPreviewProvider = uiEffectPreviewProvider,
                    uiIntentPreviewProvider = uiIntentPreviewProvider,
                    coroutineScope = coroutineScope
                )
            )
        }
    } else {
        val handler = handlerFactory()
        remember {
            StatefulScope(
                handler = handler
            )
        }
    }
}

/**
 * Composable que proporciona un `StatefulScope` y registra efectos globales y locales de UI.
 *
 * @param H Tipo del manejador.
 * @param handlerFactory Función composable que proporciona el manejador.
 * @param content Contenido composable que puede acceder al `StatefulScope` proporcionado.
 */
@Composable
fun <H: Any> Stateful(
    handlerFactory: @Composable () -> H,
    content: @Composable StatefulScope<H>.() -> Unit
) {
    StatefulComponentsProvider(
        handlerFactory = handlerFactory
    ) {
        RegisterGlobalUiEffects()
        content(localStateful())
        RegisterLocalUiEffects()
    }
}

/**
 * Composable que proporciona un `StatefulScope` utilizando un `ViewModel`.
 *
 * @param H Tipo del `ViewModel`.
 * @param content Contenido composable que puede acceder al `StatefulScope` proporcionado.
 */
@Composable
inline fun <reified H: ViewModel> Stateful(
    noinline content: @Composable StatefulScope<H>.() -> Unit
) {
    Stateful(
        handlerFactory = { hiltViewModel<H>() }
    ) {
        content()
    }
}

/**
 * Composable que proporciona un `StatefulScope` hijo utilizando un `ViewModel`.
 *
 * @param H Tipo del `ViewModel`.
 * @param content Contenido composable que puede acceder al `StatefulScope` proporcionado.
 */
@Composable
inline fun <reified H: ViewModel> ChildStateful(
    noinline content: @Composable StatefulScope<H>.() -> Unit
) {
    content(localStateful())
}