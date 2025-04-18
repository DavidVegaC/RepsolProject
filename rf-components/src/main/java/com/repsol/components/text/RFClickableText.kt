package com.repsol.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle

@Composable
fun RFClickableText(
    text: String,
    textColor: RFColor = RFColor.UxComponentColorEasternBlue,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    RFText(
        text = text,
        textStyle = RFTextStyle.Roboto(
            fontSize = 16.sp,
            color = textColor
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .clickable(onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(vertical = 8.dp)
    )
}