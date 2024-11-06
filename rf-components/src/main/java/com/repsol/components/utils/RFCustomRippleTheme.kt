package com.repsol.components.utils

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import com.repsol.components.style.RFColor

object RFCustomRippleTheme : RippleTheme {

    var color: RFColor? = null

    @Composable
    override fun defaultColor() = color?.color ?: LocalContentColor.current

    @Composable
    override fun rippleAlpha(): RippleAlpha = getAlpha()


    private fun getAlpha(): RippleAlpha {
        return RippleAlpha(
            pressedAlpha = 0.25f,
            focusedAlpha = 0.25f,
            draggedAlpha = 0.25f,
            hoveredAlpha = 0.25f
        )
    }

}