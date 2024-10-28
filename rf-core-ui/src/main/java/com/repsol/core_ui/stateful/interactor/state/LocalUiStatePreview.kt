package com.repsol.core_ui.stateful.interactor.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import com.repsol.core_platform.handler.UiState

/**
 * LocalComposition para proporcionar un estado de vista de interfaz de usuario (UI State) de vista previa.
 */
internal val LocalUiStatePreview: ProvidableCompositionLocal<Any> = compositionLocalOf {
    throw IllegalStateException("No UiState provided in preview. Use ScreenPreview() to provide a preview.")
}

/**
 * Composable que permite obtener un estado de vista de interfaz de usuario (UI State) de vista previa de tipo [S].
 *
 * @return Un [State] que representa el estado de vista de interfaz de usuario de vista previa.
 */
@Suppress("UNCHECKED_CAST")
@Composable
internal fun <S: UiState> defaultUiStatePreview(): S {
    return LocalUiStatePreview.current as S
}