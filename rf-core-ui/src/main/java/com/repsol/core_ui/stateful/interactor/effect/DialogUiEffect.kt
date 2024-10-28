package com.repsol.core_ui.stateful.interactor.effect

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.repsol.core_platform.handler.UiEffect
import com.repsol.core_ui.stateful.StatefulScope
import kotlin.reflect.KClass

/**
 * Interface que define el alcance de los efectos de UI para un Dialog.
 */
interface UiEffectDialogScope {

    /**
     * Oculta el Dialog.
     */
    fun dismiss()

    companion object {

        /**
         * Crea una instancia de `UiEffectDialogScope`.
         *
         * @param onDismissRequest Acción a ejecutar al solicitar el cierre del Dialog.
         * @return Una instancia de `UiEffectDialogScope`.
         */
        fun create(onDismissRequest: () -> Unit): UiEffectDialogScope {
            return DefaultUiEffectDialogScope(onDismissRequest)
        }
    }
}

/**
 * Implementación por defecto de `UiEffectDialogScope`.
 *
 * @param onDismissRequest Acción a ejecutar al solicitar el cierre del Dialog.
 */
private class DefaultUiEffectDialogScope(
    private val onDismissRequest: () -> Unit
): UiEffectDialogScope {

    override fun dismiss() {
        onDismissRequest()
    }
}

/**
 * Composable que muestra un Dialog para un efecto de UI específico.
 *
 * @param kClass Clase del efecto de UI.
 * @param isCancelable Indica si el Dialog puede ser cancelado. Por defecto es `true`.
 * @param isFullScreen Indica si el Dialog debe mostrarse en pantalla completa. Por defecto es `false`.
 * @param content Contenido composable que se mostrará dentro del Dialog.
 */
@Composable
fun <E: UiEffect> StatefulScope<*>.DialogUiEffect(
    kClass: KClass<E>,
    isCancelable: Boolean = true,
    isFullScreen: Boolean = false,
    content: @Composable UiEffectDialogScope.(E) -> Unit
) {
    ModalUiEffect(
        kClass = kClass,
    ) { uiEffect ->
        val modalScope = remember {
            UiEffectDialogScope.create {
                disableUiEffect(
                    kClass = kClass
                )
            }
        }
        Dialog(
            onDismissRequest = {
                modalScope.dismiss()
            },
            properties = DialogProperties(
                dismissOnBackPress = isCancelable,
                dismissOnClickOutside = isCancelable,
                usePlatformDefaultWidth = !isFullScreen
            ),
        ) {
            if (isFullScreen) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    content.invoke(modalScope, uiEffect)
                }
            } else {
                content.invoke(modalScope, uiEffect)
            }
        }
    }
}