package com.repsol.components.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.repsol.components.style.RFColor

@Composable
fun RFFilter(
    modifier: Modifier = Modifier,
    radius: Dp = 24.dp,
    backgroundColor: RFColor = RFColor.UxComponentColorWhite,
    borderColor: RFColor = RFColor.UxComponentColorGrey,
    style: RFFilterStyle = RFFilterStyle.Elevation,
    leadingContent: (@Composable () -> Unit)? = null,
    textContent: @Composable () -> Unit
) {
    RFFilterImpl(
        modifier = modifier,
        shape = RoundedCornerShape(radius),
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        style = style,
        leadingContent = leadingContent,
        textContent = textContent
    )
}

@Composable
private fun RFFilterImpl(
    modifier: Modifier = Modifier,
    shape: Shape,
    backgroundColor: RFColor = RFColor.UxComponentColorWhite,
    borderColor: RFColor = RFColor.UxComponentColorGrey,
    style: RFFilterStyle = RFFilterStyle.Elevation,
    leadingContent: (@Composable () -> Unit)? = null,
    textContent: @Composable () -> Unit
) {
    val elevation = if (style == RFFilterStyle.Elevation) 4.dp else 0.dp
    val border = if (style == RFFilterStyle.Elevation) 0.dp else 1.dp

    Box(
        modifier = modifier
            .widthIn(96.dp)
            .height(48.dp)
    ) {
        Card(
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor.color
            ),
            border = BorderStroke(border, borderColor.color),
            elevation = CardDefaults.cardElevation(
                defaultElevation = elevation
            )
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                leadingContent?.invoke()
                textContent()
            }
        }
    }

}