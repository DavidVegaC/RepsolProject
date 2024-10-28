package com.repsol.core_data.common.storage

import com.repsol.core_domain.storage.SessionStorage
import com.repsol.core_domain.storage.StorageScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class DefaultSessionStorage: SessionStorage {

    private val storage = mutableMapOf<String, ScopeValue>()
    private val subscribers = mutableMapOf<String, MutableStateFlow<Any?>>()

    private fun putValue(key: String, value: Any, scope: StorageScope) {
        storage[key] = ScopeValue(value, scope.code)
        subscribers[key]?.value = value
    }

    override fun setString(key: String, value: String, scope: StorageScope) {
        putValue(key, value, scope)
    }

    override fun setInt(key: String, value: Int, scope: StorageScope) {
        putValue(key, value, scope)
    }

    override fun setBoolean(key: String, value: Boolean, scope: StorageScope) {
        putValue(key, value, scope)
    }

    override fun setFloat(key: String, value: Float, scope: StorageScope) {
        putValue(key, value, scope)
    }

    override fun setLong(key: String, value: Long, scope: StorageScope) {
        putValue(key, value, scope)
    }

    override fun setDouble(key: String, value: Double, scope: StorageScope) {
        putValue(key, value, scope)
    }

    override fun setAny(key: String, value: Any, scope: StorageScope) {
        putValue(key, value, scope)
    }

    override fun getString(key: String): String? {
        return storage[key]?.let { it.value as? String }
    }

    override fun getString(key: String, defaultValue: String): String {
        return storage[key]?.let { it.value as? String } ?: defaultValue
    }

    override fun getInt(key: String): Int? {
        return storage[key]?.let { it.value as? Int }
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return storage[key]?.let { it.value as? Int } ?: defaultValue
    }

    override fun getBoolean(key: String): Boolean? {
        return storage[key]?.let { it.value as? Boolean }
    }

    override fun getAny(key: String): Any? {
        return storage[key]?.value
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return storage[key]?.let { it.value as? Boolean } ?: defaultValue
    }

    override fun getFloat(key: String): Float? {
        return storage[key]?.let { it.value as? Float }
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return storage[key]?.let { it.value as? Float } ?: defaultValue
    }

    override fun getLong(key: String): Long? {
        return storage[key]?.let { it.value as? Long }
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return storage[key]?.let { it.value as? Long } ?: defaultValue
    }

    override fun getDouble(key: String): Double? {
        return storage[key]?.let { it.value as? Double }
    }

    override fun getDouble(key: String, defaultValue: Double): Double {
        return storage[key]?.let { it.value as? Double } ?: defaultValue
    }

    override fun getAny(key: String, defaultValue: Any): Any {
        return storage[key]?.value ?: defaultValue
    }

    private fun getSubscriber(key: String): MutableStateFlow<Any?> {
        return subscribers.getOrPut(key) { MutableStateFlow(storage[key]?.value) }
    }

    override fun subscribeString(key: String): Flow<String?> {
        return getSubscriber(key).map { it as? String }
    }

    override fun subscribeInt(key: String): Flow<Int?> {
        return getSubscriber(key).map { it as? Int }
    }

    override fun subscribeBoolean(key: String): Flow<Boolean?> {
        return getSubscriber(key).map { it as? Boolean }
    }

    override fun subscribeFloat(key: String): Flow<Float?> {
        return getSubscriber(key).map { it as? Float }
    }

    override fun subscribeLong(key: String): Flow<Long?> {
        return getSubscriber(key).map { it as? Long }
    }

    override fun subscribeDouble(key: String): Flow<Double?> {
        return getSubscriber(key).map { it as? Double }
    }

    override fun subscribeAny(key: String): Flow<Any?> {
        return getSubscriber(key)
    }

    override fun clear(scope: StorageScope) {
        val scopes = scope.includes.map { it.code } + scope.code
        storage.filter {
            scopes.contains(it.value.scopeCode)
        }.forEach { (key, _)->
            remove(key)
        }
    }

    override fun exists(key: String): Boolean {
        return storage[key] != null
    }

    override fun changeScope(key: String, scope: StorageScope) {
        storage[key]?.let { value ->
            putValue(key, value.value, scope)
        }
    }

    override fun remove(key: String) {
        storage.remove(key)
        subscribers[key]?.value = null
    }

    private data class ScopeValue(
        val value: Any,
        val scopeCode: String
    )
}