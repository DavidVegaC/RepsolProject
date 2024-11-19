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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.button.RFButton
import com.repsol.components.button.style.RFButtonShape
import com.repsol.components.card.RFCard
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.gestor_dashboard.ui.index.CreditInfoSection
import com.repsol.gestor_dashboard.ui.index.CreditInfoSectionError
import com.repsol.gestor_dashboard.ui.index.DownloadAllPrices
import com.repsol.gestor_dashboard.ui.index.HeaderHomeSection
import com.repsol.gestor_dashboard.ui.index.IndexManagerScreen
import com.repsol.gestor_dashboard.ui.index.OptionHomeManager
import com.repsol.rf_assets.R
import com.repsol.tools.components.ReusableSpacer
import com.repsol.tools.utils.CurrencyFormatter

@Composable
fun VehicleScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {

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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HeaderSection()
            }

            item {
                FilterSection()
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterComboBox()
                    ReusableSpacer(8.dp)
                    AdvancedFilters()
                }

            }
        }
    }
}

@Composable
private fun AdvancedFilters() {

    Box(
        Modifier
            .widthIn(40.dp)
            .heightIn(40.dp)
            .background(color = RFColor.UxComponentColorDiamond.color, shape = RoundedCornerShape(20.dp))
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

    SortComboBox(
        options = options,
        selectOption = selectionOption,
        onSelected = { selectionOption = it }
    )

}

@Composable
private fun SortComboBox(
    options: List<String>,
    selectOption: String,
    onSelected: (String) -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Button(
            onClick = { expanded != expanded },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.Red,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.Gray
            )
        ) {
            RFIcon(
                painter = painterResource(R.drawable.ic_camion_filled)
            )

            ReusableSpacer(8.dp)

            RFText(
                text = selectOption
            )

            ReusableSpacer(4.dp)

            RFIcon(
                painter = painterResource(R.drawable.ic_camion_filled)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterSection() {
    var query by remember { mutableStateOf("") }
    var isAcive by remember { mutableStateOf(false) }

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

        ReusableSpacer(24.dp)

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
            active = isAcive,
            onActiveChange = { isAcive = it }
        ) { }

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
            .padding(16.dp)

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
            modifier = Modifier.fillMaxWidth()
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
