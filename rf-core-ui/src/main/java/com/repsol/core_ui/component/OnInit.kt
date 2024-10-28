package com.repsol.core_ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import com.repsol.core_ui.stateful.isNotPreview

/**
 * Composable que ejecuta un bloque de código suspendido al inicializarse.
 *
 * @param executeInPreview Indica si el bloque debe ejecutarse en modo de vista previa. Por defecto es `false`.
 * @param block Bloque de código suspendido que se ejecutará dentro de un `CoroutineScope`.
 */
@Composable
fun OnInit(
    executeInPreview: Boolean = false,
    block: suspend CoroutineScope.() -> Unit
) {
    if (executeInPreview || isNotPreview()) {
        LaunchedEffect(Unit, block)
    }
}