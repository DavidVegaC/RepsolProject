package com.repsol.components.quickactioncard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.card.RFCard
import com.repsol.components.card.RFCardStyle
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@Composable
fun RFQuickActionCard(
    modifier: Modifier = Modifier.size(80.dp),
    radius: Int = 5,
    @DrawableRes icon: Int,
    text: String,
    onClick: (() -> Unit) = {},
) {
    RFCard(
        modifier = modifier,
        radius = radius.dp,
        borderColor = RFColor.UxComponentColorTransparent,
        onClick = onClick,
        style = RFCardStyle.Outline
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        RFColor.UxComponentColorPowderBlue.color,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                RFIcon(painter = painterResource(icon))
            }

            Spacer(modifier = Modifier.size(8.dp))

            RFText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                text = text,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorEasternBlue
                ),
                maxLines = 2,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
private fun RFQuickActionCardPreview() {
    RFQuickActionCard(
        icon = android.R.drawable.ic_secure,
        text = "Convertir a QR"
    )
}