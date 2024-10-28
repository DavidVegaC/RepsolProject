package com.repsol.core_ui.stateful

import androidx.lifecycle.SavedStateHandle
import com.repsol.core_platform.handler.LoaderHandler
import com.repsol.core_platform.handler.UiEffect
import com.repsol.core_platform.handler.UiEffectHandler
import com.repsol.core_platform.handler.UiEvent
import com.repsol.core_platform.handler.UiEventHandler
import com.repsol.core_platform.handler.UiIntent
import com.repsol.core_platform.handler.UiIntentHandler
import com.repsol.core_platform.handler.UiState
import com.repsol.core_platform.handler.UiStateHandler
import com.repsol.core_platform.handler.loaderHandler
import com.repsol.core_platform.handler.uiStateHandler
import com.repsol.core_platform.handler.uiEffectHandler
import com.repsol.core_platform.handler.uiEventHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.repsol.core_ui.stateful.interactor.effect.UiEffectPreviewProvider
import com.repsol.core_ui.stateful.interactor.intent.UiIntentPreviewProvider
import com.repsol.core_ui.stateful.interactor.intent.UiIntentScope
import kotlin.reflect.KClass

/**
 * Manejador de Mock para gestionar el estado de la UI, efectos de UI, intenciones de UI y eventos de UI.
 *
 * @param defaultUiState Estado de UI predeterminado.
 * @param uiEffectPreviewProvider Proveedor de vista previa de efectos de UI.
 * @param uiIntentPreviewProvider Proveedor de vista previa de intenciones de UI.
 * @param coroutineScope Alcance de la corrutina para la ejecución de tareas asincrónicas.
 * @param uiEffectHandler Manejador de efectos de UI.
 * @param uiStateHandler Manejador de estado de UI.
 * @param uiEventHandler Manejador de eventos de UI.
 */
internal class PreviewHandler(
    defaultUiState: UiState,
    private val uiEffectPreviewProvider: UiEffectPreviewProvider,
    private val uiIntentPreviewProvider: UiIntentPreviewProvider<UiState>,
    private val coroutineScope: CoroutineScope,
    private val uiEffectHandler: UiEffectHandler = uiEffectHandler(),
    private val uiStateHandler: UiStateHandler<UiState> = uiStateHandler(SavedStateHandle()) { defaultUiState },
    private val uiEventHandler: UiEventHandler<UiEvent> = uiEventHandler()
): UiStateHandler<UiState> by uiStateHandler,
    UiEffectHandler by uiEffectHandler,
    UiIntentHandler<UiIntent>,
    LoaderHandler by loaderHandler(),
    UiEventHandler<UiEvent> by uiEventHandler {

    init {
        initializeUiEffects()
        initializeUiIntents()
    }

    /**
     * Canal para enviar intenciones de UI.
     */
    private val channel = Channel<UiIntent>(BUFFERED)

    /**
     * Ejecuta una intención de UI.
     *
     * @param intent Intención de UI a ejecutar.
     */
    override fun execUiIntent(intent: UiIntent) {
        channel.trySend(intent)
    }

    /**
     * Inicializa el manejador con un alcance de corrutina y una función de manejo de intenciones.
     *
     * @param scope Alcance de la corrutina.
     * @param handle Función que maneja las intenciones de UI.
     */
    override fun initialize(scope: CoroutineScope, handle: suspend (intent: UiIntent) -> Unit) {
        scope.launch {
            channel.receiveAsFlow().collect {
                handle(it)
            }
        }
    }

    /**
     * Ejecuta la intención de UI.
     */
    override fun UiIntent.exec() {
        execUiIntent(this)
    }

    /**
     * Inicializa los efectos de UI desde el proveedor de vista previa.
     */
    @Suppress("UNCHECKED_CAST")
    private fun initializeUiEffects(){
        uiEffectPreviewProvider.uiEffects.forEach {
            if (it.value != null) {
                enableUiEffect(it.key as KClass<UiEffect>, it.value as UiEffect)
            }
        }
    }

    /**
     * Inicializa las intenciones de UI desde el proveedor de vista previa.
     */
    private fun initializeUiIntents(){
        initialize(scope = coroutineScope) { intent ->
            uiIntentPreviewProvider.uiIntentCallbacks[intent::class]?.invoke(
                UiIntentScope(this, uiEffectHandler),
                intent
            )
        }
    }
}