package com.repsol.core_ui.stateful.interactor.effect

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.repsol.core_platform.handler.UiEffect
import com.repsol.core_platform.handler.ifEnabled
import com.repsol.core_platform.handler.isEnabled
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.repsol.core_ui.stateful.StatefulScope
import kotlin.reflect.KClass

/**
 * Interface que define el alcance de los efectos de UI para un BottomSheet.
 */
interface UiEffectBottomSheetScope {

    /**
     * Oculta el BottomSheet.
     */
    fun dismiss()

    companion object {

        /**
         * Crea una instancia de `UiEffectBottomSheetScope`.
         *
         * @param sheetState Estado del BottomSheet.
         * @param coroutineScope Alcance de la corrutina.
         * @param onDismissRequest Acción a ejecutar al solicitar el cierre del BottomSheet.
         * @return Una instancia de `UiEffectBottomSheetScope`.
         */
        @OptIn(ExperimentalMaterial3Api::class)
        fun create(
            sheetState: SheetState,
            coroutineScope: CoroutineScope,
            onDismissRequest: () -> Unit
        ): UiEffectBottomSheetScope {
            return DefaultUiEffectBottomSheetScope(
                sheetState = sheetState,
                coroutineScope = coroutineScope,
                onDismissRequest = onDismissRequest
            )
        }
    }
}

/**
 * Implementación por defecto de `UiEffectBottomSheetScope`.
 *
 * @param sheetState Estado del BottomSheet.
 * @param coroutineScope Alcance de la corrutina.
 * @param onDismissRequest Acción a ejecutar al solicitar el cierre del BottomSheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
private class DefaultUiEffectBottomSheetScope(
    private val sheetState: SheetState,
    private val coroutineScope: CoroutineScope,
    private val onDismissRequest: () -> Unit
): UiEffectBottomSheetScope {

    override fun dismiss() {
        coroutineScope.launch {
            sheetState.hide()
            onDismissRequest()
        }
    }
}

/**
 * Composable que muestra un BottomSheet para un efecto de UI específico.
 *
 * @param kClass Clase del efecto de UI.
 * @param isCancelable Indica si el BottomSheet puede ser cancelado. Por defecto es `true`.
 * @param content Contenido composable que se mostrará dentro del BottomSheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <E: UiEffect> StatefulScope<*>.BottomSheetUiEffect(
    kClass: KClass<E>,
    isCancelable: Boolean = true,
    content: @Composable UiEffectBottomSheetScope.(E) -> Unit
) {
    ModalUiEffect(
        kClass = kClass,
    ) { uiEffect ->
        val sheetState: SheetState = rememberModalBottomSheetState(
            confirmValueChange = { isCancelable }
        )
        var isShowBottomSheet by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(uiEffect) {
            isShowBottomSheet = uiEffect.isEnabled()
        }
        if (isShowBottomSheet) {
            val coroutineScope = rememberCoroutineScope()
            val modalScope = remember {
                UiEffectBottomSheetScope.create(
                    sheetState = sheetState,
                    coroutineScope = coroutineScope,
                ) {
                    disableUiEffect(
                        kClass = kClass
                    )
                }
            }
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    modalScope.dismiss()
                },
                containerColor = Color.Transparent,
                dragHandle = null,
                shape = RectangleShape,
                properties = ModalBottomSheetDefaults.properties(
                    shouldDismissOnBackPress = isCancelable,
                ),
            ) {
                uiEffect.ifEnabled {
                    content.invoke(modalScope, it)
                }
            }
        }
    }
}
