package com.repsol.flotas

import android.app.Application
import android.content.Context
import com.gigya.android.sdk.Gigya
import com.gigya.android.sdk.GigyaLogger
import com.repsol.core_data.common.storage.StorageInitializer
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltAndroidApp
class KleanApplication: Application() {
    // TODO Delete when https://github.com/google/dagger/issues/3601 is resolved.
    @Inject
    @ApplicationContext
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        StorageInitializer.initialize(context = this)

        // Gigya logs.
        GigyaLogger.setDebugMode(true)

        // This step is crucial for the SDK lifecycle. This will bing the application
        // context.
        Gigya.setApplication(this)
    }
}