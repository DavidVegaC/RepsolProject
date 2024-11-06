package com.repsol.components.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.repsol.components.style.RFColor
import com.repsol.components.utils.rfClickableOvalRipple

@Composable
fun RFIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    tint: RFColor = RFColor.UxComponentColorEasternBlue
) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .run {
                if (onClick != null) {
                    rfClickableOvalRipple {
                        onClick.invoke()
                    }
                } else {
                    this
                }
            },
        tint = tint.color
    )
}