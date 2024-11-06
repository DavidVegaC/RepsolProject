package com.repsol.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.repsol.components.button.entity.RFColorState
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.components.utils.RFCustomRippleTheme

@Composable
fun RFButtonBase(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: String,
    textStyle: RFTextStyle,
    rfColorState: RFColorState,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    colorRipple: RFColor? = null,
    icon: (@Composable () -> Unit)? = null,
    shape: Shape,
    contentModifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = when {
        rfColorState.colorStatePressed == null -> rfColorState.enabledColor
        isPressed -> rfColorState.colorStatePressed
        else -> rfColorState.enabledColor
    }

    val rippleTheme = RFCustomRippleTheme
    rippleTheme.color = colorRipple
    CompositionLocalProvider(LocalRippleTheme provides rippleTheme) {
        Button(
            modifier = Modifier
                .then(modifier),
            onClick = onClick,
            enabled = enabled,
            shape = shape,
            border = border,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = color.color,
                contentColor = rfColorState.textEnabled.color,
                disabledContainerColor = rfColorState.disabledColor.color,
                disabledContentColor = rfColorState.textColorDisabled.color
            )
        ) {
            Row(
                modifier = contentModifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                icon?.invoke()
                RFText(
                    modifier = textModifier,
                    text = text,
                    textStyle = textStyle
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun RFButtonBasePreview() {
    RFButtonBase(
        modifier = Modifier.fillMaxWidth(),
        text = "Boton",
        textStyle = RFTextStyle.RepsolBold().apply {
            style = style.copy(
                color = RFColor.UxComponentColorWhite.color
            )
        },
        rfColorState = RFColorState(
            enabledColor = RFColor.UxComponentColorEasternBlue,
            textEnabled = RFColor.UxComponentColorWhite,
            disabledColor = RFColor.UxComponentColorGainsboro,
            textColorDisabled = RFColor.UxComponentColorDarkGray,
        ),
        shape = RoundedCornerShape(4.dp),
        onClick = { }
    )
}