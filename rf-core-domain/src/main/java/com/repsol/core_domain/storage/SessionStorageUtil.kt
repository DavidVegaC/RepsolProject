package com.repsol.core_domain.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LazySessionValue<T: Any?>(
    private val key: String,
    private val scope: StorageScope = StorageScope.APP,
) : ReadWriteProperty<SessionStorage, T?> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: SessionStorage, property: KProperty<*>): T? {
        return thisRef.getAny(key) as T
    }

    override fun setValue(thisRef: SessionStorage, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.remove(key)
        } else {
            thisRef.setAny(key, value, scope = scope)
        }
    }
}

fun <T: Any> sessionValue(
    key: String,
    scope: StorageScope = StorageScope.APP
) = LazySessionValue<T>(key, scope)

class LazySessionFlowValue<T: Any>(
    private val key: String,
) : ReadOnlyProperty<SessionStorage, Flow<T?>> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: SessionStorage, property: KProperty<*>): Flow<T?> {
        return thisRef.subscribeAny(key).map { it as T? }
    }
}

fun <T: Any> sessionFlowValue(
    key: String
) = LazySessionFlowValue<T>(key)