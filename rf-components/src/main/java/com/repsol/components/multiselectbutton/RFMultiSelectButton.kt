package com.repsol.components.multiselectbutton

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectButton(
    options: List<String>,
    selectedOptions: List<String>,
    onOptionSelected: (String) -> Unit,
    onOptionDeselected: (String) -> Unit,
    leadingIcon: Int,
    modifier: Modifier = Modifier
) {

    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FlowRow(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            options.forEach { option ->
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
                    modifier = Modifier.wrapContentWidth()
                )
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

    Card(
        modifier = modifier
            .heightIn(48.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = if (isSelected) 1.dp else 0.dp,
                color = borderColor.color,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor.color
        )
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .wrapContentSize()
                .padding(horizontal = 24.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
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
