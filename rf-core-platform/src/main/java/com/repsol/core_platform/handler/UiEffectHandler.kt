package com.repsol.core_platform.handler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * Interfaz que representa un efecto de UI.
 */
interface UiEffect

/**
 * Interfaz para manejar efectos de UI.
 */
interface UiEffectHandler {

    /**
     * Registra un efecto de UI de la clase especificada.
     * @param kClass La clase del efecto de UI a registrar.
     * @return Un Flow que emite el efecto de UI.
     */
    fun <E: UiEffect> registerUiEffect(kClass: KClass<E>): Flow<E?>

    /**
     * Recupera el efecto de UI actual de la clase especificada.
     * @param kClass La clase del efecto de UI a recuperar.
     * @return El efecto de UI actual, o null si no hay ninguno establecido.
     */
    fun <E: UiEffect> getUiEffect(kClass: KClass<E>): E?

    /**
     * Verifica si un efecto de UI de la clase especificada está habilitado.
     * @param kClass La clase del efecto de UI a verificar.
     * @return True si el efecto de UI está habilitado, false en caso contrario.
     */
    fun <E: UiEffect> isUiEffectEnabled(kClass: KClass<E>): Boolean

    /**
     * Habilita un efecto de UI de la clase especificada con el valor dado.
     * @param kClass La clase del efecto de UI a habilitar.
     * @param value El valor del efecto de UI a habilitar.
     */
    fun <E: UiEffect> enableUiEffect(kClass: KClass<E>, value: E)

    /**
     * Deshabilita un efecto de UI de la clase especificada.
     * @param kClass La clase del efecto de UI a deshabilitar.
     */
    fun <E: UiEffect> disableUiEffect(kClass: KClass<E>)

    /**
     * Habilita un efecto de UI de la clase especificada con el valor dado por una duración especificada.
     * @param kClass La clase del efecto de UI a habilitar.
     * @param value El valor del efecto de UI a habilitar.
     * @param timeMillis La duración en milisegundos durante la cual el efecto de UI debe estar habilitado.
     */
    fun <E: UiEffect> enableUiEffect(kClass: KClass<E>, value: E, timeMillis: Long)

    /**
     * Desactiva todos los efectos de UI.
     */
    fun deactivateAllUiEffects()
}

/**
 * Registra un efecto de UI de la clase especificada.
 * @return Un Flow que emite el efecto de UI.
 */
inline fun <reified E: UiEffect> UiEffectHandler.registerUiEffect(): Flow<E?> {
    return registerUiEffect(E::class)
}

/**
 * Recupera el efecto de UI actual de la clase especificada.
 * @return El efecto de UI actual, o null si no hay ninguno establecido.
 */
inline fun <reified E: UiEffect> UiEffectHandler.getUiEffect(): E? {
    return getUiEffect(E::class)
}

/**
 * Verifica si un efecto de UI de la clase especificada está habilitado.
 * @return True si el efecto de UI está habilitado, false en caso contrario.
 */
inline fun <reified E: UiEffect> UiEffectHandler.isUiEffectEnabled(): Boolean {
    return isUiEffectEnabled(E::class)
}

/**
 * Habilita un efecto de UI de la clase especificada con el valor dado.
 * @param value El valor del efecto de UI a habilitar.
 */
inline fun <reified E: UiEffect> UiEffectHandler.enableUiEffect(value: E) {
    enableUiEffect(E::class, value)
}

/**
 * Deshabilita un efecto de UI de la clase especificada.
 */
inline fun <reified E: UiEffect> UiEffectHandler.disableUiEffect() {
    disableUiEffect(E::class)
}

/**
 * Habilita un efecto de UI de la clase especificada con el valor dado por una duración especificada.
 * @param value El valor del efecto de UI a habilitar.
 * @param timeMillis La duración en milisegundos durante la cual el efecto de UI debe estar habilitado.
 */
inline fun <reified E: UiEffect> UiEffectHandler.enableUiEffect(value: E, timeMillis: Long) {
    enableUiEffect(E::class, value, timeMillis)
}

/**
 * Implementación por defecto de la interfaz UiEffectHandler.
 */
private class DefaultUiEffectHandler: UiEffectHandler {

    private val lastJobs = mutableMapOf<KClass<*>, Job>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val effects: MutableMap<KClass<*>, MutableStateFlow<Any?>> = mutableMapOf()

    /**
     * Registra un efecto interno de la clase especificada.
     * @param kClass La clase del efecto a registrar.
     * @return Un MutableStateFlow que representa el efecto.
     */
    private fun <E: UiEffect> internalRegisterEffect(kClass: KClass<E>): MutableStateFlow<Any?> {
        return effects.getOrPut(kClass) { MutableStateFlow(value = null) }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <E: UiEffect> registerUiEffect(kClass: KClass<E>): Flow<E?> {
        return internalRegisterEffect(kClass).map { it as E? }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <E : UiEffect> getUiEffect(kClass: KClass<E>): E? {
        return effects[kClass]?.value as E?
    }

    override fun <E : UiEffect> isUiEffectEnabled(kClass: KClass<E>): Boolean {
        return getUiEffect(kClass) != null
    }

    override fun <E : UiEffect> enableUiEffect(kClass: KClass<E>, value: E) {
        if (isUiEffectEnabled(kClass)) return
        internalRegisterEffect(kClass).value = value
    }

    override fun <E : UiEffect> disableUiEffect(kClass: KClass<E>) {
        if (!isUiEffectEnabled(kClass)) return
        internalRegisterEffect(kClass).value = null
    }

    override fun <E : UiEffect> enableUiEffect(kClass: KClass<E>, value: E, timeMillis: Long) {
        lastJobs[kClass]?.cancel()
        lastJobs[kClass] = coroutineScope.launch {
            enableUiEffect(kClass, value)
            delay(timeMillis)
            disableUiEffect(kClass)
        }
    }

    override fun deactivateAllUiEffects() {
        effects.forEach { (_, effect) ->
            effect.value = false
        }
    }
}

/**
 * Crea una nueva instancia de DefaultUiEffectHandler.
 * @return Una nueva instancia de UiEffectHandler.
 */
fun uiEffectHandler(): UiEffectHandler {
    return DefaultUiEffectHandler()
}

/**
 * Crea una nueva instancia de DefaultUiEffectHandler.
 * @return Una nueva instancia de UiEffectHandler.
 */
fun <E: UiEffect> E?.isEnabled(): Boolean = this != null

/**
 * Función de extensión para ejecutar un bloque si el efecto de UI está habilitado.
 * @param block El bloque a ejecutar si el efecto de UI está habilitado.
 * @return El efecto de UI.
 */
inline fun <E: UiEffect> E?.ifEnabled(
    block: (E) -> Unit
): E? {
    if (this != null) {
        block(this)
    }
    return this
}

/**
 * Función de extensión para ejecutar un bloque si el efecto de UI está deshabilitado.
 * @param block El bloque a ejecutar si el efecto de UI está deshabilitado.
 * @return El efecto de UI.
 */
inline fun <E: UiEffect> E?.ifDisabled(
    block: () -> Unit
): E? {
    if (this == null) {
        block()
    }
    return this
}