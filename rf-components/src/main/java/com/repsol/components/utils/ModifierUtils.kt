package com.repsol.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.repsol.components.style.RFColor

fun Modifier.setGone(): Modifier {
    return size(0.dp)
}

fun Modifier.setInvisible(): Modifier {
    return alpha(0f)
}

@Composable
fun Modifier.conditionalModifier(
    conditional: Boolean,
    ifModifier: @Composable Modifier.() -> Modifier,
    elseModifier: @Composable Modifier.() -> Modifier = { this }
): Modifier {
    return if (conditional) {
        this.ifModifier()
    } else {
        this.elseModifier()
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.disableClickAndRipple(): Modifier = composed {
    clickable(
        enabled = false,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { },
    )
}

fun Modifier.clickableRipple(
    interactionSource: MutableInteractionSource,
    color: RFColor,
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        interactionSource = interactionSource,
        indication = rememberRipple(
            color = color.color
        ),
        onClick = { onClick.invoke() }
    )
    this then this
}

fun Modifier.addRipple(
    interactionSource: MutableInteractionSource,
    color: RFColor?,
): Modifier = composed {
    if (color != null) {
        indication(
            interactionSource = interactionSource,
            indication = rememberRipple(
                color = color.color
            )
        )
    }
    this then this
}

@Composable
fun Modifier.rfClickable(
    onClick: () -> Unit
) = this then Modifier.clickable(
    onClick = onClick,
    indication = rememberRipple(
        color = RFColor.UxComponentColorEasternBlue.color.copy(alpha = 0.3f)
    ),
    interactionSource = remember {
        MutableInteractionSource()
    }
)

@Composable
fun Modifier.rfClickableOvalRipple(
    onClick: () -> Unit
) = this then Modifier.clickable(
    onClick = onClick,
    indication = rememberRipple(
        color = RFColor.UxComponentColorEasternBlue.color.copy(alpha = 0.3f),
        bounded = false
    ),
    interactionSource = remember {
        MutableInteractionSource()
    }
)