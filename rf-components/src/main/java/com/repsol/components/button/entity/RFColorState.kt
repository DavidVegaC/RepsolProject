package com.repsol.components.button.entity

import com.repsol.components.style.RFColor

data class RFColorState(
    val textEnabled: RFColor,
    val textColorDisabled: RFColor,
    val enabledColor: RFColor,
    val disabledColor: RFColor,
    val colorStatePressed: RFColor? = null,
)