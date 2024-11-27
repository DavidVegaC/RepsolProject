package com.repsol.components.multiselectbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.card.RFCard
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@Composable
fun MultiSelectButton(
    options: List<String>,
    selectedOptions: List<String>,
    onOptionSelected: (String) -> Unit,
    onOptionDeselected: (String) -> Unit,
    leadingIcon: Int,
    modifier: Modifier = Modifier
) {

    val maxButtonsInRow = if (options.size > 3) 2 else options.size
    val rows = options.chunked(maxButtonsInRow)

    Column(Modifier.fillMaxWidth()) {
        rows.forEach { rowOptions ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                rowOptions.forEach { option ->
                    val isSelected = selectedOptions.contains(option)

                    SelectableButton(
                        text = option,
                        isSelected = isSelected,
                        onClick = {
                            if (isSelected) {
                                onOptionDeselected(option)
                            } else {
                                onOptionSelected(option)
                            }
                        },
                        leadingIcon = leadingIcon,
                        modifier = Modifier.weight(1f)
                    )
                }

            }

            Spacer(Modifier.height(8.dp))
        }
    }


}

@Composable
private fun SelectableButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    leadingIcon: Int,
    modifier: Modifier = Modifier
) {
    val backgroundColor =
        if (isSelected) RFColor.UxComponentColorLinen else RFColor.UxComponentColorWhite
    val borderColor =
        if (isSelected) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorDarkGray
    val textColor =
        if (isSelected) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal

    RFCard(
        modifier = modifier
            .heightIn(48.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, borderColor.color, RoundedCornerShape(24.dp))
            .background(backgroundColor.color),
        color = backgroundColor,
        borderColor = borderColor,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            if (isSelected) {
                RFIcon(
                    painter = painterResource(leadingIcon),
                    tint = RFColor.UxComponentColorSafetyOrange
                )
            }

            RFText(
                text = text,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = textColor
                ),
                textAlign = TextAlign.Center
            )

        }
    }
}
