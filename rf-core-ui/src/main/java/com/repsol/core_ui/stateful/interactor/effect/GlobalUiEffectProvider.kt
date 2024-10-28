package com.repsol.core_ui.stateful.interactor.effect

import androidx.compose.runtime.Composable
import com.repsol.core_ui.stateful.LocalStatefulScope
import com.repsol.core_ui.stateful.StatefulScope

object GlobalUiEffectProvider {

    private var registerUiEffect: (@Composable StatefulScope<*>.() -> Unit)? = null

    /**
     * Operador de invocación para registrar un efecto de UI global.
     *
     * @param effect Función composable que define el efecto de UI a registrar.
     */
    operator fun invoke(
        effect: @Composable StatefulScope<*>.() -> Unit
    ) {
        registerUiEffect = effect
    }

    /**
     * Función composable que registra el efecto de UI global en el `StatefulScope` actual.
     */
    @Composable
    fun Register() {
        val scope = LocalStatefulScope.current
        registerUiEffect?.invoke(scope)
    }
}

/**
 * Función composable que registra los efectos de UI globales.
 */
@Composable
fun RegisterGlobalUiEffects() {
    GlobalUiEffectProvider.Register()
}