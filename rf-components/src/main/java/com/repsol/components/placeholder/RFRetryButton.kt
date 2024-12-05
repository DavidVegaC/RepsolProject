package com.repsol.components.placeholder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFSpannableText
import com.repsol.components.text.RFText
import com.repsol.rf_components.R

@Composable
fun RFRetryButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RFText(
            text = text,
            highlight = RFSpannableText(
                text = text,
                onClick = onClick,
                textStyle = RFTextStyle.RobotoBold(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorSafetyOrange,
                ),
                underLine = true
            ),
            textAlign = TextAlign.Center,
            modifier = modifier
                .clickable(onClick = onClick)
                .padding(vertical = 8.dp)
        )

        Spacer(Modifier.size(4.dp))

        RFIcon(
            painter = painterResource(icon),
            tint = RFColor.UxComponentColorSafetyOrange
        )
    }

}