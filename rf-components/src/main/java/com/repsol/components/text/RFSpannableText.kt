package com.repsol.components.text

import com.repsol.components.style.RFTextStyle

data class RFSpannableText(
    val text: String,
    val textStyle: RFTextStyle,
    val underLine: Boolean = false,
    val onClick: () -> Unit
)