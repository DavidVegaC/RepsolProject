package com.repsol.components.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.rf_components.R

@Composable
fun RFCustomCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    backgroundColor: RFColor = RFColor.UxComponentColorLightGray,
    borderColor: RFColor = RFColor.UxComponentColorBlackPearl,
    checkmarkColor: RFColor = RFColor.UxComponentColorBlueLagoon
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(
                color = if (checked) backgroundColor.color else RFColor.UxComponentColorDiamond.color,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor.color,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onCheckedChange?.invoke(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            RFIcon(
                painter = painterResource(R.drawable.ic_checked),
                tint = checkmarkColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}