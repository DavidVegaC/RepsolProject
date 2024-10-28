package com.repsol.core_ui.stateful.interactor.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import com.repsol.core_platform.handler.UiEffect
import kotlin.reflect.KClass

internal class UiEffectStore {

    /**
     * Almacén de efectos modales de UI, mapeados por su clase.
     */
    val modalUiEffectStore = mutableStateMapOf<KClass<*>, @Composable () -> Unit>()

    /**
     * Vincula un composable con un efecto de UI.
     *
     * @param E Tipo del efecto de UI.
     * @param kClass Clase del efecto de UI.
     * @param effect Función composable que define el efecto de UI.
     */
    fun <E: UiEffect> bindComposableWithUiEffect(
        kClass: KClass<E>,
        effect: @Composable () -> Unit
    ) {
        modalUiEffectStore[kClass] = effect
    }
}

/**
 * Registra los efectos de UI locales en el contexto de composición actual.
 */
@Composable
internal fun RegisterLocalUiEffects() {
    val uiEffectStore = LocalUiEffectStore.current
    uiEffectStore.modalUiEffectStore.forEach {
        it.value()
    }
}