package com.repsol.tools.utils


import com.repsol.core_domain.storage.SessionStorage

object UserSession {
    const val ID_CLIENT = "idClient"
    const val NAME = "name"
    const val LAST_NAME = "lastName"
    const val BUSINESS_NAME = "businessName"

    //Obtiene solo dato especifico
    fun getUserData(key: String): String {
        return SessionStorage.getString(key).orEmpty()
    }

    fun getFullName(): String {
        val firtsName =  SessionStorage.getString(NAME).orEmpty()
        val lastName = SessionStorage.getString(LAST_NAME).orEmpty()

        return "$firtsName $lastName".trim()
    }
}