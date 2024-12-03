package com.repsol.components.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RFTopBar(
    title: String,
    iconDefault: Painter? = null,
    onBackClick: (() -> Unit)? = null,
    contentDescription: String? = null,
    titleColor: RFColor = RFColor.UxComponentColorSafetyOrange,
    backgroundColor: RFColor = RFColor.UxComponentColorWhite
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                RFText(
                    text = title,
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 18.sp,
                        color = titleColor
                    )
                )
            }

        },
        navigationIcon = {
            if (onBackClick != null && iconDefault != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = iconDefault,
                        contentDescription = contentDescription,
                        tint = RFColor.UxComponentColorSafetyOrange.color
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor.color
        )
    )
}

@Composable
private fun PreviewUse() {
    RFTopBar(title = "Detalle de tarjeta")
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    PreviewUse()
}