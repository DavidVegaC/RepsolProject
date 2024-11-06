package com.repsol.components.utils

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import com.repsol.components.text.RFSpannableText

internal fun getSpanStyle(highlight: RFSpannableText): SpanStyle {

    if (highlight.underLine) {
        return SpanStyle(
            color = highlight.textStyle.style.color,
            fontStyle = highlight.textStyle.style.fontStyle,
            fontFamily = highlight.textStyle.style.fontFamily,
            fontSize = highlight.textStyle.style.fontSize,
            fontFeatureSettings = highlight.textStyle.style.fontFeatureSettings,
            fontWeight = highlight.textStyle.style.fontWeight,
            fontSynthesis = highlight.textStyle.style.fontSynthesis,
            drawStyle = highlight.textStyle.style.drawStyle,
            shadow = highlight.textStyle.style.shadow,
            background = highlight.textStyle.style.background,
            localeList = highlight.textStyle.style.localeList,
            textGeometricTransform = highlight.textStyle.style.textGeometricTransform,
            baselineShift = highlight.textStyle.style.baselineShift,
            letterSpacing = highlight.textStyle.style.letterSpacing,
            textDecoration = TextDecoration.Underline,
        )
    }
    return highlight.textStyle.style.toSpanStyle()
}