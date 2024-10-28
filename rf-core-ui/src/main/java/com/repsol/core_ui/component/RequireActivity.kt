package com.repsol.core_ui.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

@Composable
fun requireActivity(): Activity {
    return LocalView.current.context as Activity
}