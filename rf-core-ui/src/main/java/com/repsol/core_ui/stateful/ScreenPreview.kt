package com.repsol.core_ui.stateful

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.repsol.core_platform.handler.UiState
import com.repsol.core_ui.stateful.interactor.state.LocalUiStatePreview
import com.repsol.core_ui.stateful.interactor.effect.LocalUiEffectPreview
import com.repsol.core_ui.stateful.interactor.effect.UiEffectPreviewProvider
import com.repsol.core_ui.stateful.interactor.effect.buildUiEffectPreviewProvider
import com.repsol.core_ui.stateful.interactor.intent.LocalUiIntentPreview
import com.repsol.core_ui.stateful.interactor.intent.UiIntentPreviewProvider
import com.repsol.core_ui.stateful.interactor.intent.buildUiIntentPreviewProvider

/**
 * Composición local para almacenar el estado de vista previa de la pantalla.
 */
val LocalScreenPreview: ProvidableCompositionLocal<Boolean> = compositionLocalOf {
    false
}

/**
 * Composable que verifica si está en modo de vista previa.
 *
 * @return `true` si está en modo de vista previa, `false` en caso contrario.
 */
@Composable
fun isPreview(): Boolean = LocalScreenPreview.current

/**
 * Composable que verifica si no está en modo de vista previa.
 *
 * @return `true` si no está en modo de vista previa, `false` en caso contrario.
 */
@Composable
fun isNotPreview(): Boolean = !isPreview()

/**
 * Composable que proporciona un entorno de vista previa de pantalla.
 *
 * @param uiState Estado de UI.
 * @param uiEffectProvider Proveedor de efectos de UI.
 * @param uiIntentProvider Proveedor de intenciones de UI.
 * @param content Contenido composable.
 */
@Suppress("UNCHECKED_CAST")
@Composable
fun <S: UiState> ScreenPreview(
    uiState: S,
    uiEffectProvider: UiEffectPreviewProvider.Builder.() -> Unit = { },
    uiIntentProvider: UiIntentPreviewProvider.Builder<S>.() -> Unit = { },
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalUiStatePreview provides uiState,
        LocalUiEffectPreview provides buildUiEffectPreviewProvider(uiEffectProvider),
        LocalUiIntentPreview provides buildUiIntentPreviewProvider(uiIntentProvider) as UiIntentPreviewProvider<UiState>,
        LocalScreenPreview provides true
    ) {
        content()
    }
}