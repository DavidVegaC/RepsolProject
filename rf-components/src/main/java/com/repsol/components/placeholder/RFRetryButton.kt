package com.repsol.components.placeholder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@Composable
fun RFRetryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    RFText(
        text = text,
        textStyle = RFTextStyle.Roboto(
            fontSize = 12.sp,
            color = RFColor.UxComponentColorSafetyOrange,
            textDecoration = TextDecoration.Underline
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    )

}