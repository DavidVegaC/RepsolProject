package com.repsol.core_data.common.storage

import android.content.Context
import com.repsol.core_domain.storage.LocalStorage
import com.repsol.core_domain.storage.SessionStorage

object StorageInitializer {

    fun initialize(context: Context) {
        LocalStorage.initialize(DefaultLocalStorage(context))
        SessionStorage.initialize(DefaultSessionStorage())
    }
}