package com.repsol.core_domain.storage

import kotlinx.coroutines.flow.Flow

interface SessionStorage {

    //Setters

    fun setString(
        key: String,
        value: String,
        scope: StorageScope = StorageScope.APP
    )

    fun setInt(
        key: String,
        value: Int,
        scope: StorageScope = StorageScope.APP
    )

    fun setBoolean(
        key: String,
        value: Boolean,
        scope: StorageScope = StorageScope.APP
    )

    fun setFloat(
        key: String,
        value: Float,
        scope: StorageScope = StorageScope.APP
    )

    fun setLong(
        key: String,
        value: Long,
        scope: StorageScope = StorageScope.APP
    )

    fun setDouble(
        key: String,
        value: Double,
        scope: StorageScope = StorageScope.APP
    )

    fun setAny(
        key: String,
        value: Any,
        scope: StorageScope = StorageScope.APP
    )

    //Getters nullables

    fun getString(key: String): String?

    fun getInt(key: String): Int?

    fun getBoolean(key: String): Boolean?

    fun getFloat(key: String): Float?

    fun getLong(key: String): Long?

    fun getDouble(key: String): Double?

    fun getAny(key: String): Any?

    //Getters with default values

    fun getString(key: String, defaultValue: String): String

    fun getInt(key: String, defaultValue: Int): Int

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun getFloat(key: String, defaultValue: Float): Float

    fun getLong(key: String, defaultValue: Long): Long

    fun getDouble(key: String, defaultValue: Double): Double

    fun getAny(key: String, defaultValue: Any): Any

    // Subscriptions

    fun subscribeString(key: String): Flow<String?>

    fun subscribeInt(key: String): Flow<Int?>

    fun subscribeBoolean(key: String): Flow<Boolean?>

    fun subscribeFloat(key: String): Flow<Float?>

    fun subscribeLong(key: String): Flow<Long?>

    fun subscribeDouble(key: String): Flow<Double?>

    fun subscribeAny(key: String): Flow<Any?>

    fun clear(scope: StorageScope = StorageScope.APP)

    fun exists(key: String): Boolean

    fun changeScope(key: String, scope: StorageScope)

    fun remove(key: String)

    companion object: SessionStorage {

        private var instanceNullable: SessionStorage? = null
        private val instance: SessionStorage get() = instanceNullable
            ?: throw IllegalStateException("SessionStorage has not been initialized yet")

        fun initialize(sessionStorage: SessionStorage) {
            instanceNullable = sessionStorage
        }

        override fun setString(key: String, value: String, scope: StorageScope) {
            instance.setString(key, value, scope)
        }

        override fun setInt(key: String, value: Int, scope: StorageScope) {
            instance.setInt(key, value, scope)
        }

        override fun setBoolean(key: String, value: Boolean, scope: StorageScope) {
            instance.setBoolean(key, value, scope)
        }

        override fun setFloat(key: String, value: Float, scope: StorageScope) {
            instance.setFloat(key, value, scope)
        }

        override fun setLong(key: String, value: Long, scope: StorageScope) {
            instance.setLong(key, value, scope)
        }

        override fun setDouble(key: String, value: Double, scope: StorageScope) {
            instance.setDouble(key, value, scope)
        }

        override fun setAny(key: String, value: Any, scope: StorageScope) {
            instance.setAny(key, value, scope)
        }

        override fun getString(key: String): String? {
            return instance.getString(key)
        }

        override fun getInt(key: String): Int? {
            return instance.getInt(key)
        }

        override fun getBoolean(key: String): Boolean? {
            return instance.getBoolean(key)
        }

        override fun getFloat(key: String): Float? {
            return instance.getFloat(key)
        }

        override fun getLong(key: String): Long? {
            return instance.getLong(key)
        }

        override fun getDouble(key: String): Double? {
            return instance.getDouble(key)
        }

        override fun getAny(key: String): Any? {
            return instance.getAny(key)
        }

        override fun getString(key: String, defaultValue: String): String {
            return instance.getString(key, defaultValue)
        }

        override fun getInt(key: String, defaultValue: Int): Int {
            return instance.getInt(key, defaultValue)
        }

        override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
            return instance.getBoolean(key, defaultValue)
        }

        override fun getFloat(key: String, defaultValue: Float): Float {
            return instance.getFloat(key, defaultValue)
        }

        override fun getLong(key: String, defaultValue: Long): Long {
            return instance.getLong(key, defaultValue)
        }

        override fun getDouble(key: String, defaultValue: Double): Double {
            return instance.getDouble(key, defaultValue)
        }

        override fun getAny(key: String, defaultValue: Any): Any {
            return instance.getAny(key, defaultValue)
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

        override fun subscribeAny(key: String): Flow<Any?> {
            return instance.subscribeAny(key)
        }

        override fun clear(scope: StorageScope) {
            instance.clear(scope)
        }

        override fun exists(key: String): Boolean {
            return instance.exists(key)
        }

        override fun changeScope(key: String, scope: StorageScope) {
            instance.changeScope(key, scope)
        }

        override fun remove(key: String) {
            instance.remove(key)
        }
    }
}