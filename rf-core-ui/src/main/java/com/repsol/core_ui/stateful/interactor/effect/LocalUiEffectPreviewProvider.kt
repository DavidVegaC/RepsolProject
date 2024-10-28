package com.repsol.core_ui.stateful.interactor.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.repsol.core_platform.handler.UiEffect
import kotlin.reflect.KClass

/**
 * Composición local para almacenar el UiEffectPreviewProvider.
 * Proporciona una instancia predeterminada de UiEffectPreviewProvider con un mapa vacío.
 */
internal val LocalUiEffectPreview: ProvidableCompositionLocal<UiEffectPreviewProvider> = compositionLocalOf {
    UiEffectPreviewProvider(mapOf())
}

/**
 * Proveedor de vista previa de efectos de UI.
 *
 * @param uiEffects Mapa de efectos de UI.
 */
class UiEffectPreviewProvider internal constructor(
    internal val uiEffects: Map<KClass<*>, Any?>
) {

    /**
     * Constructor de `UiEffectPreviewProvider`.
     */
    class Builder {

        private val uiEffects = mutableMapOf<KClass<*>, Any?>()

        /**
         * Agrega un efecto de UI al proveedor.
         *
         * @param uiEffect Par de clase de efecto de UI y el efecto de UI.
         */
        fun <E: UiEffect> add(uiEffect: Pair<KClass<E>, E>) {
            uiEffects[uiEffect.first] = uiEffect.second
        }

        /**
         * Proporciona un efecto de UI.
         */
        inline fun <reified E: UiEffect> E.provide() {
            add(E::class to this)
        }

        /**
         * Construye el `UiEffectPreviewProvider`.
         *
         * @return Una instancia de `UiEffectPreviewProvider`.
         */
        fun build(): UiEffectPreviewProvider {
            return UiEffectPreviewProvider(uiEffects)
        }
    }
}

/**
 * Construye un `UiEffectPreviewProvider` utilizando un bloque de construcción.
 *
 * @param block Bloque de construcción.
 * @return Una instancia de `UiEffectPreviewProvider`.
 */
internal fun buildUiEffectPreviewProvider(block: UiEffectPreviewProvider.Builder.() -> Unit): UiEffectPreviewProvider {
    val builder = UiEffectPreviewProvider.Builder()
    block(builder)
    return builder.build()
}

/**
 * Composable que proporciona el `UiEffectPreviewProvider` actual.
 *
 * @return El `UiEffectPreviewProvider` actual.
 */
@Composable
internal fun uiEffectPreviewProvider(): UiEffectPreviewProvider {
    return LocalUiEffectPreview.current
}