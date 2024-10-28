package com.repsol.core_ui.stateful.interactor.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalUiEffectStore = staticCompositionLocalOf<UiEffectStore> {
    error("No UiEffectStore provided")
}

/**
 * Proveedor de UiEffectStore para el contexto de composición.
 *
 * @param content Contenido composable que puede acceder al UiEffectStore proporcionado.
 */
@Composable
internal fun LocalUiEffectProvider(
    content: @Composable () -> Unit
) {
    val uiEffectStore = remember { UiEffectStore() }
    CompositionLocalProvider(
        LocalUiEffectStore provides uiEffectStore,
    ) {
        content()
    }
}