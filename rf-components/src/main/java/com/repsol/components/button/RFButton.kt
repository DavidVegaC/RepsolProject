package com.repsol.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.repsol.components.button.style.RFButtonOnColor
import com.repsol.components.button.style.RFButtonShape
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.rf_components.R

@Composable
fun RFButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    rfButtonStyle: RFTextButtonStyle = RFTextButtonStyle.Primary,
    textStyle: RFTextStyle = RFTextStyle.RFButtonTextStyle(),
    rfShape: RFButtonShape = RFButtonShape.Regular,
    rfOnColor: RFButtonOnColor = RFButtonOnColor.Background,
    contentModifier: Modifier = Modifier,
    borderStrokeWidth: Dp? = null
) {
    val colorState = when (rfButtonStyle) {
        RFTextButtonStyle.Primary -> rfOnColor.getPrimaryColorState()
        RFTextButtonStyle.Secondary -> rfOnColor.getSecondaryColorState()
        RFTextButtonStyle.Text -> rfOnColor.getTextColorState()
    }

    val strokeColor = when {
        rfOnColor == RFButtonOnColor.Primary && enabled -> RFColor.UxComponentColorWhite
        rfOnColor == RFButtonOnColor.Primary -> RFColor.UxComponentColorGrey
        rfOnColor == RFButtonOnColor.Background && enabled -> RFColor.UxComponentColorEasternBlue
        rfOnColor == RFButtonOnColor.Background -> RFColor.UxComponentColorDarkGray
        else -> RFColor.UxComponentColorEasternBlue
    }

    val stroke = when (rfButtonStyle) {
        RFTextButtonStyle.Secondary -> BorderStroke(width = borderStrokeWidth ?: 2.dp, color = strokeColor.color)
        else -> null
    }

    val colorRipple = if (RFButtonOnColor.Primary == rfOnColor) {
        RFColor.UxComponentRippleColorOnPrimary
    } else {
        RFColor.UxComponentRippleColor
    }

    RFButtonBase(
        modifier = Modifier.then(modifier),
        text = text,
        onClick = onClick,
        enabled = enabled,
        border = stroke,
        textStyle = textStyle,
        colorRipple = colorRipple,
        rfColorState = colorState,
        shape = rfShape.getShape(),
        icon = icon,
        contentModifier = contentModifier
    )
}

@Composable
@Preview
private fun RFButtonPrimaryPreview() {
    RFButton(
        modifier = Modifier.padding(bottom = 12.dp),
        text = "Enabled - Rectangle",
        rfButtonStyle = RFTextButtonStyle.Secondary,
        icon = {
               Icon(
                   modifier = Modifier.size(20.dp),
                   painter = painterResource(id = R.drawable.ic_rf_compose_cancel),
                   contentDescription = "",
               )
        },
        onClick = {  }
    )
}