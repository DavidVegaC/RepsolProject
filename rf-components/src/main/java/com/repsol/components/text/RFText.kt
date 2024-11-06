package com.repsol.components.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.util.getHighlight

@Composable
fun RFText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: RFTextStyle = RFTextStyle.Repsol(),
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    highlight: RFSpannableText? = null,
    textAlign: TextAlign? = null,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    if (highlight == null) {
        Text(
            modifier = modifier,
            text = text,
            style = textStyle.style,
            maxLines = maxLines,
            overflow = overflow,
            textAlign = textAlign,
            onTextLayout = {
                onTextLayout?.invoke(it)
            }
        )
        return
    }

    val annotatedString: AnnotatedString = text.getHighlight(highlight)
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        maxLines = maxLines,
        overflow = overflow,
        style = textStyle.style,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.let {
                highlight.onClick()
            }
        },
        onTextLayout = {
            onTextLayout?.invoke(it)
        }
    )
}