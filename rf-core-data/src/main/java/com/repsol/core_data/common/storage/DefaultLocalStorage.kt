package com.repsol.core_data.common.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.repsol.core_domain.storage.LocalStorage
import com.repsol.core_domain.storage.StorageScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class DefaultLocalStorage(
    private val context: Context
): LocalStorage {

    private val Context.store by preferencesDataStore(name = "local_storage.db")
    private val store get() = context.store

    override suspend fun setString(key: String, value: String, scope: StorageScope) {
        store.edit {
            it[stringPreferencesKey(key)] = ScopeValue(
                value = value,
                scopeCode = scope.code
            ).let(Json::encodeToString)
        }
    }

    override suspend fun setInt(key: String, value: Int, scope: StorageScope) {
        setString(key, value.toString(), scope)
    }

    override suspend fun setBoolean(key: String, value: Boolean, scope: StorageScope) {
        setString(key, value.toString(), scope)
    }

    override suspend fun setFloat(key: String, value: Float, scope: StorageScope) {
        setString(key, value.toString(), scope)
    }

    override suspend fun setLong(key: String, value: Long, scope: StorageScope) {
        setString(key, value.toString(), scope)
    }

    override suspend fun setDouble(key: String, value: Double, scope: StorageScope) {
        setString(key, value.toString(), scope)
    }

    override suspend fun getString(key: String): String? {
        return store.data.map {
            it[stringPreferencesKey(key)]
        }.firstOrNull()?.let { Json.decodeFromString<ScopeValue>(it).value }
    }

    override suspend fun getString(key: String, defaultValue: String): String {
        return getString(key) ?: defaultValue
    }

    override suspend fun getInt(key: String): Int? {
        return getString(key)?.toIntOrNull()
    }

    override suspend fun getInt(key: String, defaultValue: Int): Int {
        return getInt(key) ?: defaultValue
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return getString(key)?.toBooleanStrictOrNull()
    }

    override suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return getBoolean(key) ?: defaultValue
    }

    override suspend fun getFloat(key: String): Float? {
        return getString(key)?.toFloatOrNull()
    }

    override suspend fun getFloat(key: String, defaultValue: Float): Float {
        return getFloat(key) ?: defaultValue
    }

    override suspend fun getLong(key: String): Long? {
        return getString(key)?.toLongOrNull()
    }

    override suspend fun getLong(key: String, defaultValue: Long): Long {
        return getLong(key) ?: defaultValue
    }

    override suspend fun getDouble(key: String): Double? {
        return getString(key)?.toDoubleOrNull()
    }

    override suspend fun getDouble(key: String, defaultValue: Double): Double {
        return getDouble(key) ?: defaultValue
    }

    override fun subscribeString(key: String): Flow<String?> {
        return store.data.map {
            it[stringPreferencesKey(key)]?.let { value ->
                Json.decodeFromString<ScopeValue>(value).value
            }
        }
    }

    override fun subscribeInt(key: String): Flow<Int?> {
        return subscribeString(key).map { it?.toIntOrNull() }
    }

    override fun subscribeBoolean(key: String): Flow<Boolean?> {
        return subscribeString(key).map { it?.toBooleanStrictOrNull() }
    }

    override fun subscribeFloat(key: String): Flow<Float?> {
        return subscribeString(key).map { it?.toFloatOrNull() }
    }

    override fun subscribeLong(key: String): Flow<Long?> {
        return subscribeString(key).map { it?.toLongOrNull() }
    }

    override fun subscribeDouble(key: String): Flow<Double?> {
        return subscribeString(key).map { it?.toDoubleOrNull() }
    }

    override suspend fun clear(scope: StorageScope) {
        val scopes = scope.includes.map { it.code } + scope.code
        store.edit {
            it.asMap().keys.filter { key ->
                val scopeValue: ScopeValue? = it[key]?.let { value ->
                    Json.decodeFromString(value.toString())
                }
                scopes.contains(scopeValue?.scopeCode)
            }.forEach { key ->
                it.remove(key)
            }
        }
    }

    override suspend fun exists(key: String): Boolean {
        return getString(key) != null
    }

    override suspend fun changeScope(key: String, scope: StorageScope) {
        store.edit {
            val scopeValue: ScopeValue? = it[stringPreferencesKey(key)]?.let(Json::decodeFromString)
            if (scopeValue != null && scopeValue.scope != scope) {
                setString(key, scopeValue.value, scope)
            }
        }
    }

    override suspend fun remove(key: String) {
        store.edit {
            it.remove(stringPreferencesKey(key))
        }
    }

    @Serializable
    private data class ScopeValue(
        val value: String,
        val scopeCode: String
    ) {
        val scope = StorageScope.firstByCode(scopeCode)
    }
}