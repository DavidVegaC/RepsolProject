package com.repsol.core_ui.stateful.interactor.effect

import androidx.compose.runtime.Composable
import com.repsol.core_platform.handler.UiEffect
import com.repsol.core_platform.handler.ifEnabled
import com.repsol.core_ui.stateful.StatefulScope
import com.repsol.core_ui.component.OnInit
import kotlin.reflect.KClass

/**
 * Composable que muestra un efecto modal de UI específico.
 *
 * @param kClass Clase del efecto de UI.
 * @param content Contenido composable que se mostrará cuando el efecto de UI esté habilitado.
 */
@Composable
internal fun <E: UiEffect> StatefulScope<*>.ModalUiEffect(
    kClass: KClass<E>,
    content: @Composable (E) -> Unit
) {
    val store = LocalUiEffectStore.current
    OnInit(
        executeInPreview = true
    ) {
        store.bindComposableWithUiEffect(
            kClass = kClass,
            effect = {
                uiEffect(kClass).value.ifEnabled {
                    content(it)
                }
            }
        )
    }
}