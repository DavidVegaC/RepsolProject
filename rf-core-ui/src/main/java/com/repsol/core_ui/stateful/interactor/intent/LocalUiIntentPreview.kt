package com.repsol.core_ui.stateful.interactor.intent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.repsol.core_platform.handler.UiIntent
import com.repsol.core_platform.handler.UiState
import kotlin.reflect.KClass

/**
 * Composición local para almacenar el proveedor de vista previa de intenciones de UI.
 */
internal val LocalUiIntentPreview: ProvidableCompositionLocal<UiIntentPreviewProvider<UiState>> = compositionLocalOf {
    UiIntentPreviewProvider(mapOf())
}


/**
 * Proveedor de vista previa de intenciones de UI.
 *
 * @param uiIntentCallbacks Mapa de callbacks de intenciones de UI.
 */
class UiIntentPreviewProvider<S: UiState> internal constructor(
    internal val uiIntentCallbacks: Map<KClass<*>, suspend UiIntentScope<S>.(Any) -> Unit>
) {

    /**
     * Constructor de `UiIntentPreviewProvider`.
     */
    class Builder<S: UiState> {

        private val uiIntentCallbacks = mutableMapOf<KClass<*>, suspend UiIntentScope<S>.(Any) -> Unit>()

        /**
         * Agrega una intención de UI al proveedor.
         *
         * @param uiIntent Par de clase de intención de UI y el callback de intención de UI.
         */
        @Suppress("UNCHECKED_CAST")
        fun <I: UiIntent> add(
            uiIntent: Pair<KClass<I>, suspend UiIntentScope<S>.(I) -> Unit>
        ) {
            uiIntentCallbacks[uiIntent.first] = uiIntent.second as suspend (UiIntentScope<S>.(Any) -> Unit)
        }

        /**
         * Proporciona una intención de UI.
         *
         * @param callback Callback de intención de UI.
         */
        inline fun <reified I: UiIntent> Builder<S>.provide(
            noinline callback: suspend UiIntentScope<S>.(I) -> Unit
        ) {
            add(I::class to callback)
        }

        /**
         * Construye el `UiIntentPreviewProvider`.
         *
         * @return Una instancia de `UiIntentPreviewProvider`.
         */
        fun build(): UiIntentPreviewProvider<S> {
            return UiIntentPreviewProvider(uiIntentCallbacks)
        }
    }
}

/**
 * Construye un `UiIntentPreviewProvider` utilizando un bloque de construcción.
 *
 * @param block Bloque de construcción.
 * @return Una instancia de `UiIntentPreviewProvider`.
 */
internal fun <S: UiState> buildUiIntentPreviewProvider(block: UiIntentPreviewProvider.Builder<S>.() -> Unit): UiIntentPreviewProvider<S> {
    val builder = UiIntentPreviewProvider.Builder<S>()
    block(builder)
    return builder.build()
}

/**
 * Composable que proporciona el `UiIntentPreviewProvider` actual.
 *
 * @return El `UiIntentPreviewProvider` actual.
 */
@Composable
internal fun uiIntentPreviewProvider(): UiIntentPreviewProvider<UiState> {
    return LocalUiIntentPreview.current
}