package com.repsol.gestor_dashboard.ui.index

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.button.RFButton
import com.repsol.components.button.style.RFButtonShape
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.components.utils.conditionalModifier
import com.repsol.components.utils.setGone
import com.repsol.rf_assets.R
import com.repsol.tools.components.DisplayImage
import com.repsol.tools.components.ReusableSpacer
import com.repsol.tools.utils.CurrencyFormatter

@Composable
fun IndexManagerScreen(modifier: Modifier = Modifier) {

    val isErorDatosCredit = false // mockEscenario
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {

        Image(
            painter = painterResource(R.drawable.content_eclipse_gradient),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HeaderHomeSection()
            }

            item {
                if (isErorDatosCredit) {
                    CreditInfoSectionError()
                } else {
                    CreditInfoSection()
                }

            }

            item {
                OptionHomeManager()
            }

            item {
                DownloadAllPrices()
            }
        }
    }
}

@Composable
fun DownloadAllPrices() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        RFButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            text = stringResource(R.string.download_all_prices),
            onClick = {},
            icon = {
                RFIcon(
                    painter = painterResource(R.drawable.ic_ees),
                    tint = RFColor.UxComponentColorWhite
                )
            },
            rfShape = RFButtonShape.Round
        )
    }
}

@Composable
fun OptionHomeManager() {
    val actions = listOf(
        "Asignar Saldo" to R.drawable.ic_dollar,
        "Crear nueva tarjeta" to R.drawable.ic_pay,
        "Aprobar Topes" to R.drawable.ic_less,
        "Cambiar estado " to R.drawable.ic_repeat
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        RFIcon(
            painter = painterResource(R.drawable.ic_save),
            tint = RFColor.UxComponentColorDarkOrange

        )

        ReusableSpacer(8.dp)

        RFText(
            text = stringResource(R.string.what_do_you_want_to_do),
            textStyle = RFTextStyle.Roboto(
                fontSize = 20.sp,
                color = RFColor.UxComponentColorWhite
            )
        )

    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 400.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(actions.size) { index ->
            val (label, icon) = actions[index]

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .background(
                        RFColor.UxComponentColorWhite.color,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                RFColor.UxComponentColorPowderBlue.color,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {

                        RFIcon(
                            painter = painterResource(id = icon)
                        )

                    }

                    ReusableSpacer(4.dp)

                    RFText(
                        text = label,
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 12.sp,
                            color = RFColor.UxComponentColorEasternBlue
                        )
                    )
                }
            }
        }
    }

}

@Composable
fun CreditInfoSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(300.dp)
            .background(RFColor.UxComponentColorWhite.color, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CreditContentTextAndGraph()
            ReusableSpacer(16.dp)
            DebtWarning()
        }
    }
}

@Composable
fun CreditInfoSectionError() {
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(300.dp)
            .background(RFColor.UxComponentColorWhite.color, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column {
            DisplayImage(
                Modifier.fillMaxWidth(),
                R.drawable.image_home_error,

                )

            ReusableSpacer(24.dp)

            RFText(
                text = stringResource(R.string.view_not_available_at_this_time),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorDarkOrange
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }
}

@Composable
fun DebtWarning() {
    Column(Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .conditionalModifier(
                conditional = false,
                ifModifier = { setGone() },
                elseModifier = { this }
            )
            .align(Alignment.CenterHorizontally)
            .wrapContentWidth()
            .border(
                width = 1.dp,
                color = RFColor.UxComponentColorLightPink.color,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                RFColor.UxComponentColorMistyRose.color,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 8.dp), contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.wrapContentWidth()
            ) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_danger),
                    tint = RFColor.UxComponentColorCrimson
                )

                RFText(
                    text = stringResource(R.string.debtWarning),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 12.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )

                RFIcon(
                    painter = painterResource(R.drawable.ic_eye_open),
                    tint = RFColor.UxComponentColorCharcoal
                )
            }
        }
    }

}

@Composable
fun CreditContentTextAndGraph() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(64.dp)
                        .background(
                            RFColor.UxComponentColorDarkOrange.color,
                            shape = RoundedCornerShape(16.dp)
                        )
                )

                ReusableSpacer(8.dp)

                Column {
                    RFText(
                        text = stringResource(R.string.approved_line),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 12.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        text = CurrencyFormatter.formatCurrencyInSoles(750000.00),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 18.sp,
                            color = RFColor.UxComponentColorDarkOrange
                        )
                    )

                    RFText(
                        text = stringResource(R.string.deadline),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 12.sp,
                            color = RFColor.UxComponentColorGainsboro
                        )
                    )
                }

            }

            ReusableSpacer(8.dp)

            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(64.dp)
                        .background(
                            RFColor.UxComponentColorDarkCerulean.color,
                            shape = RoundedCornerShape(16.dp)
                        )
                )

                ReusableSpacer(8.dp)

                Column {
                    RFText(
                        text = stringResource(R.string.approved_line),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 12.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        text = CurrencyFormatter.formatCurrencyInSoles(137582.63),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 18.sp,
                            color = RFColor.UxComponentColorDarkCerulean
                        )
                    )
                }

            }

            ReusableSpacer(8.dp)

            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(64.dp)
                        .background(
                            RFColor.UxComponentColorDarkCerulean.color,
                            shape = RoundedCornerShape(16.dp)
                        )
                )

                ReusableSpacer(8.dp)

                Column {
                    RFText(
                        text = stringResource(R.string.approved_line),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 12.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        text = "81%",
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 18.sp,
                            color = RFColor.UxComponentColorDarkCerulean
                        )
                    )
                }

            }
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(start = 16.dp)
                .background(RFColor.UxComponentColorDarkGray.color, shape = RoundedCornerShape(50))
        ) {
            Text(
                text = "Espacio para la grafica",
                color = Color.Gray
            )
        }


    }

}


@Composable
fun HeaderHomeSection() {
    val username = "Alejandra"
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_home),
                    tint = RFColor.UxComponentColorDarkOrange
                )

                ReusableSpacer(8.dp)

                RFText(
                    text = stringResource(R.string.welcome_user, username),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 20.sp,
                        color = RFColor.UxComponentColorWhite
                    )
                )
            }

            RFText(
                text = "Logistica Integral del Perú S.A.C",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorWhite
                ),
                modifier = Modifier.padding(start = 32.dp)
            )
        }

        DisplayImage(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .align(Alignment.CenterEnd),
            R.drawable.ic_world

        )

    }
}

@Preview
@Composable
fun DefaultHomeScreenPreview() {
    IndexManagerScreen()
}