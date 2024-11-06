package com.repsol.components.text.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.repsol.components.text.RFSpannableText
import com.repsol.components.utils.getSpanStyle

fun String.getHighlightMultiple(
    highlight: Array<out RFSpannableText>
): AnnotatedString {
    val rawText = this
    if (highlight.isEmpty()) return AnnotatedString(rawText)
    return buildAnnotatedString {

        append(rawText)
        for (highlighted in highlight) {
            val startIndex = rawText.indexOf(highlighted.text)
            if (startIndex == -1) continue

            val endIndex = startIndex + highlighted.text.length

            val spanStyle = getSpanStyle(highlighted)
            addStyle(
                style = spanStyle,
                start = startIndex,
                end = endIndex,
            )
            addStringAnnotation(
                tag = highlighted.text,
                annotation = highlighted.text,
                start = startIndex,
                end = endIndex
            )
        }

    }
}