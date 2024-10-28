package com.repsol.core_ui.stateful.loader

import androidx.compose.runtime.Composable
import com.repsol.core_platform.Loadable
import com.repsol.core_platform.handler.LoaderHandler
import com.repsol.core_ui.component.OnInit
import com.repsol.core_ui.component.requireActivity
import com.repsol.core_ui.stateful.StatefulScope
import com.repsol.core_ui.stateful.isNotPreview

@Composable
internal fun StatefulScope<*>.LoaderEventConnector() {
    handler.let { handler ->
        if (handler is LoaderHandler && isNotPreview()) {
            val activity = requireActivity()
            if (activity is Loadable) {
                OnInit(
                    executeInPreview = true
                ) {
                    handler.onLoading {
                        activity.isLoading = it
                    }
                }
            }
        }
    }
}