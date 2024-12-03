package com.repsol.gestor_dashboard.ui.detailvehicle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
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
import com.repsol.rf_assets.R
import com.repsol.tools.components.DisplayImage
import com.repsol.tools.components.ReusableSpacer

@Composable
fun DetailVehicleScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(230.dp)
                    .background(RFColor.UxComponentColorSafetyOrange.color)
            ) {
                HeaderContent()
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionCard()
            }

            HorizontalDivider(color = RFColor.UxComponentColorGainsboro.color)

            CardDataVehicle()

            CardsPerformanceAndCapacity()

            CardInfoDriver()

        }
    }


}

@Composable
fun CardInfoDriver() {
    RFCard(
        Modifier
            .fillMaxWidth()
            .heightIn(200.dp)
            .padding(16.dp),
        clickable = false
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DisplayImage(
                modifier = Modifier
                    .align(Alignment.Start),
                R.drawable.avatar
            )

            ReusableSpacer(12.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "Dagoberto Márquez",
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )

            ReusableSpacer(24.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "DNI: 43992410",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )

            ReusableSpacer(8.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "Licencia: 245780654322",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )

            ReusableSpacer(8.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "Expiración de licencia: 09/05/2024",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )
        }
    }
}

@Composable
fun CardsPerformanceAndCapacity() {
    Row(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RFCard(
            Modifier
                .weight(1f)
                .heightIn(94.dp),
            clickable = false
        ) {

            Column(Modifier.padding(16.dp)) {
                RFText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Rendimiento",
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )

                ReusableSpacer(8.dp)

                RFText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "35 KM/gal",
                    textStyle = RFTextStyle.Repsol(
                        fontSize = 28.sp,
                        color = RFColor.UxComponentColorDarkOrange
                    )
                )
            }

        }

        RFCard(
            Modifier
                .weight(1f)
                .heightIn(94.dp),
            clickable = false
        ) {
            Column(Modifier.padding(16.dp)) {
                RFText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Capacidad",
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )

                ReusableSpacer(8.dp)

                RFText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "150 gal",
                    textStyle = RFTextStyle.Repsol(
                        fontSize = 28.sp,
                        color = RFColor.UxComponentColorDarkOrange
                    )
                )
            }
        }
    }
}

@Composable
private fun CardDataVehicle() {
    RFCard(
        Modifier
            .fillMaxWidth()
            .heightIn(200.dp)
            .padding(16.dp),
        clickable = false
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DisplayImage(
                modifier = Modifier
                    .align(Alignment.Start),
                R.drawable.truck
            )

            ReusableSpacer(12.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "Datos del Vehículo",
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )

            ReusableSpacer(24.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "Año fabricación: 2018",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )

            ReusableSpacer(8.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "Combustible: Premium",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )

            ReusableSpacer(8.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = "Fecha modificación: 10/08/2023",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )
        }
    }
}

@Composable
private fun ActionCard() {
    RFCard(
        modifier = Modifier.size(width = 100.dp, height = 100.dp),
        radius = 16.dp,
        style = RFCardStyle.Elevation
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                Modifier
                    .size(32.dp)
                    .background(
                        RFColor.UxComponentColorDiamond.color,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                RFIcon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(R.drawable.ic_user_filled)
                )
            }
            ReusableSpacer(8.dp)
            RFText(
                text = "Conductor",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorEasternBlue
                )
            )
        }
    }

    ReusableSpacer(24.dp)

    RFCard(
        modifier = Modifier.size(width = 100.dp, height = 100.dp),
        radius = 16.dp,
        style = RFCardStyle.Elevation
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                Modifier
                    .size(32.dp)
                    .background(
                        RFColor.UxComponentColorDiamond.color,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                RFIcon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(R.drawable.ic_repeat)
                )
            }

            ReusableSpacer(8.dp)
            RFText(
                text = "Cambiar estado",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorEasternBlue
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun HeaderContent() {
    Column(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        RFText(
            text = "Resumen de Vehículo",
            textStyle = RFTextStyle.Repsol(
                fontSize = 32.sp,
                color = RFColor.UxComponentColorWhite
            )
        )

        RFCard(
            Modifier
                .fillMaxWidth()
                .heightIn(94.dp),
            clickable = false
        ) {
            Column(Modifier.padding(24.dp)) {
                Row {
                    RFText(
                        modifier = Modifier.weight(1f),
                        text = "Mercedes-Benz",
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 16.sp,
                            color = RFColor.UxComponentColorDarkGray
                        )
                    )

                    Box(
                        Modifier
                            .heightIn(24.dp)
                            .widthIn(32.dp)
                            .background(
                                RFColor.UxComponentColorGreen.color,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        RFIcon(
                            painter = painterResource(R.drawable.ic_camion_filled),
                            tint = RFColor.UxComponentColorWhite
                        )
                    }

                }

                ReusableSpacer(12.dp)

                Row(verticalAlignment = Alignment.CenterVertically) {

                    RFText(
                        text = "ZEC-542",
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 28.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    ReusableSpacer(24.dp)

                    Column {
                        RFText(
                            text = "Modelo",
                            textStyle = RFTextStyle.Roboto(
                                fontSize = 12.sp,
                                color = RFColor.UxComponentColorDarkGray
                            )
                        )

                        ReusableSpacer(8.dp)

                        RFText(
                            text = "Axor 2041 LS",
                            textStyle = RFTextStyle.Roboto(
                                fontSize = 12.sp,
                                color = RFColor.UxComponentColorCharcoal
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultDetailVehiclePreview() {
    DetailVehicleScreen()
}
