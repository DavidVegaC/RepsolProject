package com.repsol.components.filterspinner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.repsol.components.style.RFColor
import com.repsol.components.utils.conditionalModifier

@Composable
fun FilterSpinner(
    modifier: Modifier = Modifier,
    radius: Dp = 24.dp,
    backgroundColor: RFColor = RFColor.UxComponentColorWhite,
    borderColor: RFColor = RFColor.UxComponentColorGrey,
    rippleColor: RFColor = RFColor.UxComponentRippleColor,
    style: RFFilterSpinnerStyle = RFFilterSpinnerStyle.Elevation,
    clickable: Boolean = true,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    leadingContent: (@Composable () -> Unit)? = null,
    textContent: @Composable () -> Unit,
    trailingContent: (@Composable () -> Unit)? = null
) {
    FilterSpinner(
        modifier = modifier,
        shape = RoundedCornerShape(radius),
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        rippleColor = rippleColor,
        style = style,
        clickable = clickable,
        options = options,
        onOptionSelected = onOptionSelected,
        leadingContent = leadingContent,
        textContent = textContent,
        trailingContent = trailingContent
    )
}

@Composable
private fun FilterSpinner(
    modifier: Modifier = Modifier,
    shape: Shape,
    backgroundColor: RFColor = RFColor.UxComponentColorWhite,
    borderColor: RFColor = RFColor.UxComponentColorGrey,
    rippleColor: RFColor = RFColor.UxComponentRippleColor,
    style: RFFilterSpinnerStyle = RFFilterSpinnerStyle.Elevation,
    clickable: Boolean,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    leadingContent: (@Composable () -> Unit)? = null,
    textContent: @Composable () -> Unit,
    trailingContent: (@Composable () -> Unit)? = null
) {
    val elevation = if (style == RFFilterSpinnerStyle.Elevation) 4.dp else 0.dp
    val border = if (style == RFFilterSpinnerStyle.Elevation) 0.dp else 1.dp
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    var spinnerWidth by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                spinnerWidth = with(localDensity) { coordinates.size.width.toDp() }
            }

    ) {
        Card(
            modifier = Modifier
                .conditionalModifier(
                    conditional = clickable,
                    ifModifier = {
                        clickable(
                            interactionSource = interactionSource,
                            indication = rememberRipple(
                                bounded = true,
                                color = rippleColor.color
                            )
                        ) { expanded = true }
                    }
                ),
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
                trailingContent?.invoke()
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(spinnerWidth)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }

}