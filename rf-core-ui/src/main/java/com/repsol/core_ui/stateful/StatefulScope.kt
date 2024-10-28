package com.repsol.core_ui.stateful

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.repsol.core_platform.handler.UiEffect
import com.repsol.core_platform.handler.UiEffectHandler
import com.repsol.core_platform.handler.UiEvent
import com.repsol.core_platform.handler.UiEventHandler
import com.repsol.core_platform.handler.UiIntent
import com.repsol.core_platform.handler.UiIntentHandler
import com.repsol.core_platform.handler.UiState
import com.repsol.core_platform.handler.UiStateHandler
import com.repsol.core_platform.handler.ifEnabled
import com.repsol.core_platform.handler.isEnabled
import com.repsol.core_ui.component.OnInit
import com.repsol.core_ui.stateful.interactor.effect.BottomSheetUiEffect
import com.repsol.core_ui.stateful.interactor.effect.DialogUiEffect
import com.repsol.core_ui.stateful.interactor.state.LocalUiState
import com.repsol.core_ui.stateful.interactor.effect.UiEffectBottomSheetScope
import com.repsol.core_ui.stateful.interactor.effect.UiEffectDialogScope
import kotlin.reflect.KClass

/**
 * Clase que representa un `StatefulScope` que maneja diferentes aspectos del estado de la UI, eventos, intenciones y efectos.
 *
 * @param H Tipo del manejador.
 * @param handler Manejador que implementa las interfaces necesarias para manejar el estado de la UI, eventos, intenciones y efectos.
 */
class StatefulScope<H: Any> internal constructor(
    internal val handler: Any
) {

    //UiState Api

    /**
     * Establece el estado de la UI utilizando una función reductora.
     *
     * @param S Tipo del estado de la UI.
     * @param H Tipo del manejador de estado de la UI.
     * @param reduce Función que reduce el estado de la UI.
     */
    @Suppress("UNCHECKED_CAST")
    fun <S: UiState, H: UiStateHandler<S>> StatefulScope<H>.setUiState(
        reduce: S.() -> S
    ) {
        return (handler as H).setUiState(reduce(handler.uiState))
    }

    /**
     * Obtiene el estado de la UI actual como un `State`.
     *
     * @param S Tipo del estado de la UI.
     * @param H Tipo del manejador de estado de la UI.
     * @return El estado de la UI actual.
     */
    @Suppress("UNCHECKED_CAST")
    @Composable
    fun <S: UiState, H: UiStateHandler<S>> StatefulScope<H>.uiState(): State<S> {
        return LocalUiState.current as State<S>
    }

    //UiEvent Api

    /**
     * Registra un manejador para eventos de la UI.
     *
     * @param E Tipo del evento de la UI.
     * @param H Tipo del manejador de eventos de la UI.
     * @param handle Función que maneja el evento de la UI.
     */
    @Suppress("UNCHECKED_CAST")
    @Composable
    fun <E: UiEvent, H: UiEventHandler<E>> StatefulScope<H>.OnUiEvent(
        handle: (uiEvent: E) -> Unit
    ){
        OnInit {
            (handler as H).setOnUiEvent(handle)
        }
    }

    //UiIntent Api

    /**
     * Ejecuta una intención de la UI.
     *
     * @param I Tipo de la intención de la UI.
     * @param H Tipo del manejador de intenciones de la UI.
     * @param intent Intención de la UI a ejecutar.
     */
    @Suppress("UNCHECKED_CAST")
    fun <I: UiIntent, H: UiIntentHandler<I>> StatefulScope<H>.execUiIntent(intent: I) {
        (handler as H).execUiIntent(intent)
    }

    //UiEffect Api

    /**
     * Desactiva un efecto de la UI.
     *
     * @param E Tipo del efecto de la UI.
     * @param kClass Clase del efecto de la UI.
     */
    fun <E: UiEffect> StatefulScope<*>.disableUiEffect(
        kClass: KClass<E>
    ) {
        if (handler is UiEffectHandler) {
            handler.disableUiEffect(kClass)
        } else {
            throw IllegalStateException("Handler must implement UiEffectHandler")
        }
    }

    /**
     * Desactiva un efecto de la UI.
     *
     * @param E Tipo del efecto de la UI.
     */
    inline fun <reified E: UiEffect> StatefulScope<*>.disableUiEffect() {
        disableUiEffect(kClass = E::class)
    }

    /**
     * Activa un efecto de la UI.
     *
     * @param E Tipo del efecto de la UI.
     * @param kClass Clase del efecto de la UI.
     * @param value Valor del efecto de la UI.
     * @param timeMillis Tiempo en milisegundos para activar el efecto, puede ser nulo.
     */
    fun <E: UiEffect> StatefulScope<*>.enableUiEffect(
        kClass: KClass<E>,
        value: E,
        timeMillis: Long? = null
    ) {
        if (handler is UiEffectHandler) {
            if (timeMillis == null) {
                handler.enableUiEffect(kClass, value)
            } else {
                handler.enableUiEffect(kClass, value, timeMillis)
            }
        } else {
            throw IllegalStateException("Handler must implement UiEffectHandler")
        }
    }

    /**
     * Activa un efecto de la UI.
     *
     * @param E Tipo del efecto de la UI.
     * @param value Valor del efecto de la UI.
     * @param timeMillis Tiempo en milisegundos para activar el efecto, puede ser nulo.
     */
    inline fun <reified E: UiEffect> StatefulScope<*>.enableUiEffect(
        value: E,
        timeMillis: Long? = null
    ) {
        enableUiEffect(
            kClass = E::class,
            value = value,
            timeMillis = timeMillis
        )
    }

    /**
     * Desactiva todos los efectos de la UI.
     *
     * @param H Tipo del manejador de efectos de la UI.
     */
    @Suppress("UNCHECKED_CAST")
    fun <H: UiEffectHandler> StatefulScope<*>.disableAllUiEffects() {
        (handler as H).deactivateAllUiEffects()
    }

    /**
     * Obtiene un efecto de la UI como un `State`.
     *
     * @param E Tipo del efecto de la UI.
     * @param kClass Clase del efecto de la UI.
     * @return El efecto de la UI actual.
     */
    @Composable
    fun <E: UiEffect> StatefulScope<*>.uiEffect(
        kClass: KClass<E>,
    ): State<E?> {
        return if (handler is UiEffectHandler) {
            handler.registerUiEffect(kClass).collectAsState(
                initial = handler.getUiEffect(kClass)
            )
        } else {
            throw IllegalStateException("Handler must implement UiEffectHandler")
        }
    }

    /**
     * Obtiene un efecto de la UI como un `State`.
     *
     * @param E Tipo del efecto de la UI.
     * @return El efecto de la UI actual.
     */
    @Composable
    inline fun <reified E: UiEffect> StatefulScope<*>.uiEffect(): State<E?> {
        return uiEffect(kClass = E::class)
    }

    /**
     * Verifica si un efecto de la UI está habilitado.
     *
     * @param E Tipo del efecto de la UI.
     * @return `State` que indica si el efecto de la UI está habilitado.
     */
    @Composable
    inline fun <reified E: UiEffect> StatefulScope<*>.uiEffectIsEnabled(): State<Boolean> {
        val uiEffect by uiEffect<E>()
        val isEnabledState = remember { mutableStateOf(false) }
        LaunchedEffect(uiEffect) {
            isEnabledState.value = uiEffect.isEnabled()
        }
        return isEnabledState
    }

    /**
     * Composable que ejecuta un contenido si un efecto de la UI está habilitado.
     *
     * @param E Tipo del efecto de la UI.
     * @param content Contenido composable a ejecutar si el efecto de la UI está habilitado.
     */
    @Composable
    inline fun <reified E: UiEffect> StatefulScope<*>.UiEffectIsEnabled(
        content: @Composable (E) -> Unit
    ) {
        val effect by uiEffect<E>()
        effect.ifEnabled { content(it) }
    }

    /**
     * Composable que muestra un `BottomSheet` si un efecto de la UI está habilitado.
     *
     * @param E Tipo del efecto de la UI.
     * @param isCancelable Indica si el `BottomSheet` es cancelable.
     * @param content Contenido composable a mostrar en el `BottomSheet`.
     */
    @Composable
    inline fun <reified E: UiEffect> StatefulScope<*>.BottomSheetUiEffect(
        isCancelable: Boolean = true,
        noinline content: @Composable UiEffectBottomSheetScope.(E) -> Unit
    ) {
        BottomSheetUiEffect(
            kClass = E::class,
            isCancelable = isCancelable,
            content = content
        )
    }

    /**
     * Composable que muestra un `Dialog` si un efecto de la UI está habilitado.
     *
     * @param E Tipo del efecto de la UI.
     * @param isCancelable Indica si el `Dialog` es cancelable.
     * @param isFullScreen Indica si el `Dialog` es de pantalla completa.
     * @param content Contenido composable a mostrar en el `Dialog`.
     */
    @Composable
    inline fun <reified E: UiEffect> StatefulScope<*>.DialogUiEffect(
        isCancelable: Boolean = true,
        isFullScreen: Boolean = false,
        noinline content: @Composable UiEffectDialogScope.(E) -> Unit
    ) {
        DialogUiEffect(
            kClass = E::class,
            isCancelable = isCancelable,
            isFullScreen = isFullScreen,
            content = content
        )
    }
}