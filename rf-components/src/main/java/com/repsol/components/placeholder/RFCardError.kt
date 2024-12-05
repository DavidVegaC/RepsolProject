package com.repsol.components.placeholder

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.rf_components.R

@Composable
fun RFCardError(
    @DrawableRes image: Int,
    @DrawableRes icon: Int,
    label: String,
    labelRetry:String,
    contentDescription: String? = null,
    height: Dp = 300.dp,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
            .background(RFColor.UxComponentColorWhite.color, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(image),
                contentDescription = contentDescription
            )

            Spacer(Modifier.size(24.dp))

            RFText(
                text = label,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorDarkOrange
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.size(24.dp))

            RFRetryButton(
                text = labelRetry,
                icon = icon,
                onClick = onClick
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    RFCardError(
        image = R.drawable.ic_rf_compose_cancel,
        icon = R.drawable.ic_rf_compose_cancel,
        label = "Vista no disponible en estos momentos",
        labelRetry = "Reintentar"
    ){}
}


