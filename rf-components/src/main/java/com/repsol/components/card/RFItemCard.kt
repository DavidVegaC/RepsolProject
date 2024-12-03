package com.repsol.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.rf_components.R

@Composable
fun RFItemCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    typeKey: String,
    valueKey: String,
    @DrawableRes icon: Int,
    isClickable: Boolean = true,
    onClick: () -> Unit = {},
    idStatus: String,
) {
    RFCard(
        modifier = modifier,
        onClick = onClick,
        style = RFCardStyle.Outline,
        borderColor = RFColor.UxComponentColorTransparent,
        clickable = isClickable,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Column(Modifier.weight(9f)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column (
                        modifier = Modifier.weight(8.5f)
                    ) {
                        RFText(
                            text = title,
                            textStyle = RFTextStyle.Roboto(
                                fontSize = 16.sp,
                                color = RFColor.UxComponentColorDarkGray
                            )
                        )

                        Spacer(Modifier.size(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            RFText(
                                modifier = Modifier.padding(end = 5.dp),
                                text = subtitle,
                                maxLines = 2,
                                textStyle = RFTextStyle.Roboto(
                                    fontSize = 28.sp,
                                    color = RFColor.UxComponentColorCharcoal
                                )
                            )

                            if (valueKey.isNotBlank()) {
                                Spacer(Modifier.size(10.dp))

                                Column {
                                    RFText(
                                        text = typeKey,
                                        textStyle = RFTextStyle.Roboto(
                                            fontSize = 14.sp,
                                            color = RFColor.UxComponentColorDarkGray
                                        )
                                    )
                                    Spacer(Modifier.size(4.dp))
                                    RFText(
                                        text = valueKey,
                                        textStyle = RFTextStyle.Roboto(
                                            fontSize = 14.sp,
                                            color = RFColor.UxComponentColorCharcoal
                                        )
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        Modifier
                            .height(24.dp)
                            .width(32.dp)
                            .background(
                                Status.findById(idStatus).rfColor.color,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        RFIcon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(icon),
                            tint = RFColor.UxComponentColorWhite
                        )
                    }
                }
            }
            if (isClickable) {
                RFIcon(
                    modifier = Modifier.weight(1f).align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_arrow_right),
                )
            }
        }
    }
}

@Composable
@Preview
private fun RFItemCardPreview() {
    RFItemCard(
        title = "Mercedes Benz",
        subtitle = "ZEC-54232",
        typeKey = "Combustible",
        valueKey = "Regular",
        icon = android.R.drawable.ic_secure,
        idStatus = "1"
    )
}