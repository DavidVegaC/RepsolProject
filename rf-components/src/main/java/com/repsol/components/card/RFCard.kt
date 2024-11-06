package com.repsol.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.components.utils.conditionalModifier

@Composable
fun RFCard(
    modifier: Modifier = Modifier,
    radius: Dp = 8.dp,
    color: RFColor = RFColor.UxComponentColorWhite,
    borderColor: RFColor = RFColor.UxComponentColorGrey,
    ripple: RFColor = RFColor.UxComponentRippleColor,
    style: RFCardStyle = RFCardStyle.Elevation,
    clickable: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    RFCard(
        shape = RoundedCornerShape(radius),
        modifier = modifier,
        color = color,
        borderColor = borderColor,
        ripple = ripple,
        style = style,
        clickable = clickable,
        onClick = onClick,
        content = content
    )
}

@Composable
fun RFCard(
    shape: Shape,
    modifier: Modifier = Modifier,
    color: RFColor = RFColor.UxComponentColorWhite,
    borderColor: RFColor = RFColor.UxComponentColorGrey,
    ripple: RFColor = RFColor.UxComponentRippleColor,
    style: RFCardStyle = RFCardStyle.Elevation,
    clickable: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val elevation = if (style == RFCardStyle.Elevation) 4.dp else 0.dp
    val border = if (style == RFCardStyle.Elevation) 0.dp else 1.dp
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = modifier
            .conditionalModifier(
                conditional = clickable,
                ifModifier = {
                    clickable(
                        interactionSource = interactionSource,
                        indication = rememberRipple(
                            bounded = true,
                            color = ripple.color
                        ),
                        onClick = { onClick?.invoke() }
                    )
                }
            ),
        border = BorderStroke(border, borderColor.color),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = color.color
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        content()
    }
}

@Composable
@Preview
private fun RFCardPreview() {
    RFCard(
        modifier = Modifier.fillMaxWidth(),
        style = RFCardStyle.Elevation,
        onClick = { }
    ) {
        RFText(
            modifier = Modifier.padding(24.dp),
            text = "Works",
            textStyle = RFTextStyle.Repsol()
        )
    }
}