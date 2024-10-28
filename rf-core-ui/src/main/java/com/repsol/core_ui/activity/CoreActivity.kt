package com.repsol.core_ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.repsol.core_platform.Loadable
import kotlinx.coroutines.flow.MutableStateFlow

abstract class CoreActivity: AppCompatActivity(), Loadable {

    protected val loadingFlow = MutableStateFlow(false)

    override var isLoading: Boolean
        get() = loadingFlow.value
        set(value) {
            loadingFlow.value = value
        }
}