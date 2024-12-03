package com.repsol.driver_dashboard.ui.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.card.RFCard
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.Stateful
import com.repsol.rf_assets.R
import com.repsol.tools.components.DisplayImage
import com.repsol.tools.components.ReusableSpacer

@Composable
fun IndexDriverScreen(modifier: Modifier = Modifier) = Stateful<IndexDriverViewModel> {
    Box(
        modifier
            .fillMaxSize()
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                HeaderProfileHomeDriver()
            }

            item {
                DriverProfile()
            }

            item {
                DriverCardDetails()
                DriverCardStopsAndControl()
                DriverCardBalanceAndHighDate()
                DriverCostCenter()
            }

            item {
                DriverList()
            }

        }


    }
}

//mock lazy column luego se retira
data class Item(
    val image: Int,
    val placa: String,
    val marca: String,
    val modelo: String,
    val tipVeh: String
)

@Composable
private fun DriverList() {

    val listVehiculos = listOf(

        Item(
            R.drawable.truck,
            "ZEC-453",
            "Mercedes Benz",
            "Axor 2041 LS",
            "Tracto-Camión"
        ),
        Item(
            R.drawable.truck,
            "ZEC-453",
            "Mercedes Benz",
            "Axor 2041 LS",
            "Tracto-Camión"
        ),
        Item(
            R.drawable.truck,
            "ZEC-453",
            "Mercedes Benz",
            "Axor 2041 LS",
            "Tracto-Camión"
        )
        ,Item(
            R.drawable.truck,
            "ZEC-453",
            "Mercedes Benz",
            "Axor 2041 LS",
            "Tracto-Camión"
        )
    )

    LazyRow(Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(listVehiculos) { item ->
            RFCard (
                Modifier.widthIn(150.dp)
                    .heightIn(200.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    DisplayImage(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(24.dp)),
                        item.image

                    )

                    RFText(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.placa,
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 16.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.marca,
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 16.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.modelo,
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 16.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.tipVeh,
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 16.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )
                }

            }
        }
    }

    ReusableSpacer(24.dp)
}

@Composable
private fun DriverCostCenter() = ChildStateful<IndexDriverViewModel> {

    val uiState by uiState()

    RFCard(
        Modifier
            .fillMaxWidth()
            .heightIn(94.dp)
            .padding(16.dp),
        clickable = false
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.driver_cost_center),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )

            ReusableSpacer(4.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.costCenter,
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )

        }
    }
}

@Composable
private fun DriverCardBalanceAndHighDate() = ChildStateful<IndexDriverViewModel> {

    val uiState by uiState()
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
                    text = stringResource(R.string.driver_balance),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )

                ReusableSpacer(8.dp)

                RFText(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.balanceAmount,
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
                    text = stringResource(R.string.driver_high_date),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )

                ReusableSpacer(8.dp)

                RFText(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.activationDate,
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
private fun DriverCardStopsAndControl() = ChildStateful<IndexDriverViewModel> {

    val uiState by uiState()

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
                R.drawable.card_stops_control
            )

            ReusableSpacer(12.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.driver_stops_control),
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )

            ReusableSpacer(24.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.stops_days, uiState.controlTypeDays),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )

            ReusableSpacer(8.dp)

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.driver_stops_limit_amount, uiState.stopsLimitAmount),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )
        }
    }
}

@Composable
private fun DriverCardDetails() = ChildStateful<IndexDriverViewModel> {

    val uiState by uiState()

    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_card_pass),
                    tint = RFColor.UxComponentColorDarkOrange
                )

                ReusableSpacer(8.dp)

                RFText(
                    text = stringResource(R.string.driver_card_details),
                    textStyle = RFTextStyle.RobotoMedium(
                        fontSize = 20.sp,
                        color = RFColor.UxComponentColorMidnightBlue
                    )
                )

            }

            ReusableSpacer(24.dp)

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
                            text = uiState.cardDriver,
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
                                painter = painterResource(R.drawable.ic_card_pass),
                                tint = RFColor.UxComponentColorWhite
                            )
                        }

                    }

                    ReusableSpacer(12.dp)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RFText(
                            text = uiState.cardNumber,
                            textStyle = RFTextStyle.Roboto(
                                fontSize = 28.sp,
                                color = RFColor.UxComponentColorCharcoal
                            )
                        )

                        ReusableSpacer(12.dp)

                        Column {
                            RFText(
                                text = "Tipo",
                                textStyle = RFTextStyle.Roboto(
                                    fontSize = 12.sp,
                                    color = RFColor.UxComponentColorDarkGray
                                )
                            )

                            ReusableSpacer(8.dp)

                            RFText(
                                text = uiState.cardType,
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
}

@Composable
private fun DriverProfile() = ChildStateful<IndexDriverViewModel> {

    val uiState by uiState()

    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_home),
                    tint = RFColor.UxComponentColorDarkOrange
                )

                ReusableSpacer(8.dp)

                RFText(
                    text = stringResource(R.string.welcome_user, uiState.driverName),
                    textStyle = RFTextStyle.RobotoMedium(
                        fontSize = 20.sp,
                        color = RFColor.UxComponentColorMidnightBlue
                    )
                )

            }

            RFText(
                text = uiState.businessName,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorMidnightBlue
                ),
                modifier = Modifier.padding(start = 32.dp, top = 8.dp)
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

@Composable
private fun HeaderProfileHomeDriver() {
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(73.dp)
            .background(RFColor.UxComponentColorSafetyOrange.color)
    ) {
        RFText(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp),
            text = stringResource(R.string.profile_driver),
            textStyle = RFTextStyle.Repsol(
                fontSize = 28.sp,
                color = RFColor.UxComponentColorWhite
            )
        )
    }
}


@Preview
@Composable
fun DefaultHomeDriverScreenPreview() {
    IndexDriverScreen()
}
