package com.repsol.core_domain.storage

import kotlinx.coroutines.flow.Flow

interface LocalStorage {

    //Setters

    suspend fun setString(
        key: String,
        value: String,
        scope: StorageScope = StorageScope.APP
    )

    suspend fun setInt(
        key: String,
        value: Int,
        scope: StorageScope = StorageScope.APP
    )

    suspend fun setBoolean(
        key: String,
        value: Boolean,
        scope: StorageScope = StorageScope.APP
    )

    suspend fun setFloat(
        key: String,
        value: Float,
        scope: StorageScope = StorageScope.APP
    )

    suspend fun setLong(
        key: String,
        value: Long,
        scope: StorageScope = StorageScope.APP
    )

    suspend fun setDouble(
        key: String,
        value: Double,
        scope: StorageScope = StorageScope.APP
    )

    //Getters nullables

    suspend fun getString(key: String): String?

    suspend fun getInt(key: String): Int?

    suspend fun getBoolean(key: String): Boolean?

    suspend fun getFloat(key: String): Float?

    suspend fun getLong(key: String): Long?

    suspend fun getDouble(key: String): Double?

    //Getters with default values

    suspend fun getString(key: String, defaultValue: String): String

    suspend fun getInt(key: String, defaultValue: Int): Int

    suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean

    suspend fun getFloat(key: String, defaultValue: Float): Float

    suspend fun getLong(key: String, defaultValue: Long): Long

    suspend fun getDouble(key: String, defaultValue: Double): Double

    // Subscriptions

    fun subscribeString(key: String): Flow<String?>

    fun subscribeInt(key: String): Flow<Int?>

    fun subscribeBoolean(key: String): Flow<Boolean?>

    fun subscribeFloat(key: String): Flow<Float?>

    fun subscribeLong(key: String): Flow<Long?>

    fun subscribeDouble(key: String): Flow<Double?>

    suspend fun clear(scope: StorageScope = StorageScope.APP)

    suspend fun exists(key: String): Boolean

    suspend fun changeScope(key: String, scope: StorageScope)

    suspend fun remove(key: String)

    companion object: LocalStorage {

        private var instanceNullable: LocalStorage? = null
        private val instance: LocalStorage get() = instanceNullable
            ?: throw IllegalStateException("LocalStorage has not been initialized yet")

        fun initialize(localStorage: LocalStorage) {
            instanceNullable = localStorage
        }

        override suspend fun setString(key: String, value: String, scope: StorageScope) {
            instance.setString(key, value, scope)
        }

        override suspend fun setInt(key: String, value: Int, scope: StorageScope) {
            instance.setInt(key, value, scope)
        }

        override suspend fun setBoolean(key: String, value: Boolean, scope: StorageScope) {
            instance.setBoolean(key, value, scope)
        }

        override suspend fun setFloat(key: String, value: Float, scope: StorageScope) {
            instance.setFloat(key, value, scope)
        }

        override suspend fun setLong(key: String, value: Long, scope: StorageScope) {
            instance.setLong(key, value, scope)
        }

        override suspend fun setDouble(key: String, value: Double, scope: StorageScope) {
            instance.setDouble(key, value, scope)
        }

        override suspend fun getString(key: String): String? {
            return instance.getString(key)
        }

        override suspend fun getInt(key: String): Int? {
            return instance.getInt(key)
        }

        override suspend fun getBoolean(key: String): Boolean? {
            return instance.getBoolean(key)
        }

        override suspend fun getFloat(key: String): Float? {
            return instance.getFloat(key)
        }

        override suspend fun getLong(key: String): Long? {
            return instance.getLong(key)
        }

        override suspend fun getDouble(key: String): Double? {
            return instance.getDouble(key)
        }

        override suspend fun getString(key: String, defaultValue: String): String {
            return instance.getString(key, defaultValue)
        }

        override suspend fun getInt(key: String, defaultValue: Int): Int {
            return instance.getInt(key, defaultValue)
        }

        override suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
            return instance.getBoolean(key, defaultValue)
        }

        override suspend fun getFloat(key: String, defaultValue: Float): Float {
            return instance.getFloat(key, defaultValue)
        }

        override suspend fun getLong(key: String, defaultValue: Long): Long {
            return instance.getLong(key, defaultValue)
        }

        override suspend fun getDouble(key: String, defaultValue: Double): Double {
            return instance.getDouble(key, defaultValue)
        }

        override fun subscribeString(key: String): Flow<String?> {
            return instance.subscribeString(key)
        }

        override fun subscribeInt(key: String): Flow<Int?> {
            return instance.subscribeInt(key)
        }

        override fun subscribeBoolean(key: String): Flow<Boolean?> {
            return instance.subscribeBoolean(key)
        }

        override fun subscribeFloat(key: String): Flow<Float?> {
            return instance.subscribeFloat(key)
        }

        override fun subscribeLong(key: String): Flow<Long?> {
            return instance.subscribeLong(key)
        }

        override fun subscribeDouble(key: String): Flow<Double?> {
            return instance.subscribeDouble(key)
        }

        override suspend fun clear(scope: StorageScope) {
            instance.clear(scope)
        }

        override suspend fun exists(key: String): Boolean {
            return instance.exists(key)
        }

        override suspend fun changeScope(key: String, scope: StorageScope) {
            instance.changeScope(key, scope)
        }

        override suspend fun remove(key: String) {
            instance.remove(key)
        }
    }
}