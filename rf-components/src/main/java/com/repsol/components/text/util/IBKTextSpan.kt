package com.repsol.components.text.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.repsol.components.text.RFSpannableText
import com.repsol.components.utils.getSpanStyle

fun String.getHighlight(highlight: RFSpannableText?): AnnotatedString {
    if (highlight == null) return AnnotatedString(this)
    return buildAnnotatedString {
        val startIndex = this@getHighlight.indexOf(highlight.text)
        val endIndex = startIndex + highlight.text.length

        val spanStyle: SpanStyle = getSpanStyle(highlight)
        append(this@getHighlight)
        addStyle(
            style = spanStyle,
            start = startIndex,
            end = endIndex,
        )
        addStringAnnotation(
            tag = highlight.text,
            annotation = highlight.text,
            start = startIndex,
            end = endIndex
        )
    }
}