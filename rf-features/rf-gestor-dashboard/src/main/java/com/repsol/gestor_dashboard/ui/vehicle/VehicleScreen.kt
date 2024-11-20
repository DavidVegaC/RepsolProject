package com.repsol.gestor_dashboard.ui.vehicle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.card.RFCard
import com.repsol.components.filterspinner.FilterSpinner
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.rf_assets.R
import com.repsol.tools.components.ReusableSpacer

@Composable
fun VehicleScreen(modifier: Modifier = Modifier) {

    val list = listOf(
        "Elemento1", "Elemento2",
        "Elemento3", "Elemento4",
        "Elemento5"
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(300.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    RFColor.UxComponentColorMidnightBlue.color,
                                    RFColor.UxComponentColorCerulean.color,
                                    RFColor.UxComponentColorCGBlue.color
                                )
                            )
                        )
                ) {
                    Column(Modifier.padding(16.dp)) {
                        HeaderSection()
                    }
                }

                ReusableSpacer(24.dp)
                FilterSection()
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterComboBox()
                    ReusableSpacer(8.dp)
                    AdvancedFilters()
                }

            }

            items(
                count = list.size,
                key = { index -> list[index] }
            ) { index ->
                ContainerPagination(list[index])
            }

            item {

            }

        }

    }
}

@Composable
private fun ContainerPagination(item: String) {

    Column(Modifier.padding(horizontal = 16.dp)) {
        RFCard(
            Modifier
                .fillMaxWidth()
                .heightIn(94.dp),

            ) {
            Column(Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RFText(
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

                ReusableSpacer(4.dp)

                Row(
                    Modifier.fillMaxWidth()
                ) {

                    RFText(
                        text = "ZEC-542",
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 28.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    ReusableSpacer(24.dp)

                    Column(Modifier.weight(1f)) {
                        RFText(
                            text = "Combustible",
                            textStyle = RFTextStyle.Roboto(
                                fontSize = 12.sp,
                                color = RFColor.UxComponentColorDarkGray
                            )
                        )

                        ReusableSpacer(8.dp)

                        RFText(
                            text = "Regular",
                            textStyle = RFTextStyle.Roboto(
                                fontSize = 12.sp,
                                color = RFColor.UxComponentColorCharcoal
                            )
                        )
                    }

                    RFIcon(
                        painter = painterResource(R.drawable.ic_arrow_right)
                    )
                }
            }
        }
    }
}

@Composable
private fun BackgroundGradient() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(300.dp)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        RFColor.UxComponentColorMidnightBlue.color,
                        RFColor.UxComponentColorCerulean.color,
                        RFColor.UxComponentColorCGBlue.color
                    )
                )
            )
    )
}

@Composable
private fun AdvancedFilters() {

    Box(
        Modifier
            .widthIn(40.dp)
            .heightIn(40.dp)
            .background(
                color = RFColor.UxComponentColorDiamond.color,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        RFIcon(
            painter = painterResource(R.drawable.ic_list),
            onClick = {},
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun FilterComboBox() {
    //prueba
    val options = listOf("Nombre", "Placa", "Estado")
    var selectionOption by remember { mutableStateOf("Ordenar") }

    FilterSpinner(
        radius = 24.dp,
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = RFColor.UxComponentColorDiamond.color,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                RFIcon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.ic_window),
                    tint = RFColor.UxComponentColorBlueLagoon
                )
            }
        },
        trailingContent = {
            RFIcon(
                painter = painterResource(R.drawable.ic_arrow_drop_down),
                tint = RFColor.UxComponentColorBlueLagoon
            )
        },
        clickable = true,
        options = options,
        onOptionSelected = { selectionOption = it },
        textContent = {
            RFText(
                text = selectionOption,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 14.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )
        }

    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterSection() {
    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            RFIcon(
                painter = painterResource(R.drawable.ic_list),
                tint = RFColor.UxComponentColorDarkOrange
            )

            ReusableSpacer(8.dp)

            RFText(
                text = stringResource(R.string.list_general),
                textStyle = RFTextStyle.RobotoMedium(
                    fontSize = 20.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )
        }
        //se cambiara
        SearchBar(
            placeholder = {
                RFText(
                    text = stringResource(R.string.search)
                )
            },
            query = query,
            onQueryChange = { query = it },
            onSearch = { "$query" },
            active = isActive,
            onActiveChange = { isActive = it }
        ) {}

        ReusableSpacer(24.dp)

        Row(verticalAlignment = Alignment.CenterVertically) {

            RFCard(
                Modifier
                    .widthIn(100.dp)
                    .heightIn(40.dp),
                radius = 24.dp
            ) {
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    RFIcon(
                        painter = painterResource(R.drawable.ic_camion_filled)
                    )

                    RFText(
                        text = "Activas"
                    )
                }
            }

            ReusableSpacer(12.dp)

            RFCard(
                Modifier
                    .widthIn(100.dp)
                    .heightIn(40.dp),
                radius = 24.dp
            ) {
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    RFIcon(
                        painter = painterResource(R.drawable.ic_camion_filled)
                    )

                    RFText(
                        text = "Activas"
                    )
                }
            }
        }

    }
}

@Composable
private fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            RFIcon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.ic_camion_filled),
                tint = RFColor.UxComponentColorDarkOrange
            )

            ReusableSpacer(8.dp)

            RFText(
                text = stringResource(R.string.my_vehicle),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 20.sp,
                    color = RFColor.UxComponentColorWhite
                )
            )
        }

        ReusableSpacer(24.dp)

        RFCard(
            modifier = Modifier.fillMaxWidth(),
            clickable = false
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(32.dp)
                                .background(
                                    RFColor.UxComponentColorGreen.color,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        )

                        ReusableSpacer(8.dp)

                        Column {
                            RFText(
                                text = stringResource(R.string.active),
                                textStyle = RFTextStyle.Roboto(
                                    fontSize = 12.sp,
                                    color = RFColor.UxComponentColorCharcoal
                                )
                            )

                            RFText(
                                text = "74",
                                textStyle = RFTextStyle.Roboto(
                                    fontSize = 18.sp,
                                    color = RFColor.UxComponentColorGreen
                                )
                            )
                        }

                    }

                    ReusableSpacer(8.dp)

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(32.dp)
                                .background(
                                    RFColor.UxComponentColorRed.color,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        )

                        ReusableSpacer(8.dp)

                        Column {
                            RFText(
                                text = stringResource(R.string.low),
                                textStyle = RFTextStyle.Roboto(
                                    fontSize = 12.sp,
                                    color = RFColor.UxComponentColorCharcoal
                                )
                            )

                            RFText(
                                text = "9",
                                textStyle = RFTextStyle.Roboto(
                                    fontSize = 18.sp,
                                    color = RFColor.UxComponentColorRed
                                )
                            )
                        }

                    }

                }

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(start = 16.dp)
                ) {

                    StatusProgressIndicatorVehicle(
                        progress = 74f / 83f,
                        total = 83,
                        date = "22 de octubre"
                    )
                }

            }
        }
    }
}

@Composable
private fun StatusProgressIndicatorVehicle(progress: Float, total: Int, date: String) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val startAngle = -180f
            val sweepAngle = 180f * progress

            drawArc(
                color = RFColor.UxComponentColorDarkGray.color,
                startAngle = startAngle,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 40f, cap = StrokeCap.Butt)
            )

            drawArc(
                color = RFColor.UxComponentColorGreen.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 40f, cap = StrokeCap.Butt)
            )

            drawArc(
                color = RFColor.UxComponentColorRed.color,
                startAngle = startAngle + sweepAngle,
                sweepAngle = 180f - sweepAngle,
                useCenter = false,
                style = Stroke(width = 40f, cap = StrokeCap.Butt)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RFText(
                text = stringResource(R.string.total),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )

            RFText(
                text = "$total",
                textStyle = RFTextStyle.RobotoMedium(
                    fontSize = 18.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )

            RFText(
                text = date,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorDarkGray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultVehiclePreview() {
    VehicleScreen()
}
