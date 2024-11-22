package com.repsol.gestor_dashboard.ui.index

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.repsol.components.button.RFButton
import com.repsol.components.button.style.RFButtonShape
import com.repsol.components.graph.CircleGraph
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.components.utils.conditionalModifier
import com.repsol.components.utils.setGone
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.Stateful
import com.repsol.rf_assets.R
import com.repsol.tools.components.DisplayImage
import com.repsol.tools.components.ReusableSpacer
import com.repsol.tools.utils.CurrencyFormatter
import com.repsol.tools.utils.UserSession
import com.repsol.tools.utils.toDoubleOrDefault
import com.repsol.tools.utils.toNumericValue
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiEffect as UiEffect
import com.repsol.gestor_dashboard.ui.index.interactor.IndexUiIntent as UiIntent

@Composable
fun IndexManagerScreen(modifier: Modifier = Modifier) = Stateful<IndexManagerViewModel> {
    val uiState by uiState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(500.dp)
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
                    Column(
                        Modifier.padding(horizontal = 16.dp)
                    ) {
                        HeaderHomeSection()
                        ReusableSpacer(16.dp)
                        when {
                            uiState.isLoading -> PlaceholderInfoSection()
                            !uiState.errorMessage.isNullOrEmpty() -> CreditInfoSectionError()
                            uiState.showRetry -> {execUiIntent(UiIntent.OnRetryClick)}
                            else -> CreditInfoSection()
                        }
                    }
                }
            }
            item {
                Box(
                    Modifier.layout{ measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height -110.dp.roundToPx()) {
                            placeable.placeRelative(0,-110.dp.roundToPx())
                        }
                    }
                ) {

                    Column(Modifier.fillMaxWidth()) {
                        OptionHomeManager()
                        DownloadAllPrices()
                    }

                }

            }
        }

        UiEffectIsEnabled<UiEffect.SuccessDownloadSnackbar> {
            Snackbar(
                modifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
                contentColor = RFColor.UxComponentColorWhite.color,
                containerColor = RFColor.UxComponentColorGreen.color,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RFIcon(
                        painter = rememberVectorPainter(Icons.Default.Check),
                        contentDescription = "Éxito",
                        tint = RFColor.UxComponentColorWhite,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = it.text)
                }
            }
        }

        UiEffectIsEnabled<UiEffect.ErrorDownloadSnackbar> {
            Snackbar(
                modifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
                contentColor = RFColor.UxComponentColorCharcoal.color,
                containerColor = RFColor.UxComponentColorMistyRose.color,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RFIcon(
                        painter = painterResource(R.drawable.ic_danger),
                        contentDescription = "Error",
                        tint = RFColor.UxComponentColorRed,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = it.text, color = RFColor.UxComponentColorCharcoal.color)
                }
            }
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun DownloadAllPrices() = ChildStateful<IndexManagerViewModel> {

    val writePermissionState = rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val readPermissionState = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    // Verifica si la versión de Android es menor que Android 10 (API 29)
    val isApi29OrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    val context: Context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        RFButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            text = stringResource(R.string.download_all_prices),
            onClick = {

                if (isApi29OrHigher) {
                    execUiIntent(UiIntent.OnDownloadAllPrices(context, true))
                } else {
                    if (writePermissionState.status.isGranted && readPermissionState.status.isGranted) {
                        execUiIntent(UiIntent.OnDownloadAllPrices(context, false))
                    } else {
                        writePermissionState.launchPermissionRequest()
                        readPermissionState.launchPermissionRequest()
                    }
                }
            }, icon = {
                RFIcon(
                    painter = painterResource(R.drawable.ic_ees),
                    tint = RFColor.UxComponentColorWhite
                )
            },
            rfShape = RFButtonShape.Regular
        )
    }
}

@Composable
private fun OptionHomeManager() {
    val actions = listOf(
        "Asignar Saldo" to R.drawable.ic_dollar,
        "Crear nueva tarjeta" to R.drawable.ic_pay,
        "Aprobar Topes" to R.drawable.ic_less,
        "Cambiar estado " to R.drawable.ic_repeat
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
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
private fun CreditInfoSection() {
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
private fun CreditInfoSectionError() = ChildStateful<IndexManagerViewModel> {
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

            ReusableSpacer(24.dp)

            RFButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.retry),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorWhite
                ),
                rfShape = RFButtonShape.Round,
                onClick = {execUiIntent(UiIntent.OnRetryClick)}
            )
        }

    }
}

@Composable
private fun PlaceholderInfoSection() {
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(300.dp)
            .background(RFColor.UxComponentColorWhite.color, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = RFColor.UxComponentColorDarkOrange.color,
            strokeWidth = 4.dp
        )
    }
}

@Composable
private fun DebtWarning() = ChildStateful<IndexManagerViewModel>  {
    val uiState by uiState()

    Column(Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .conditionalModifier(
                conditional = uiState.overdueDebt,
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
                    tint = RFColor.UxComponentColorRed
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
private fun CreditContentTextAndGraph() = ChildStateful<IndexManagerViewModel> {
    val uiState by uiState()
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
                        text = CurrencyFormatter.formatCurrencyInSoles((uiState.data?.lineCred).toDoubleOrDefault()),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 18.sp,
                            color = RFColor.UxComponentColorDarkOrange
                        )
                    )

                    RFText(
                        text = stringResource(R.string.deadline, (uiState.data?.paymentDeadLine!!)),
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
                        text = stringResource(R.string.available_balance),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 12.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        text = CurrencyFormatter.formatCurrencyInSoles((uiState.data?.balance).toDoubleOrDefault()),
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
                            RFColor.UxComponentColorIrisBlue.color,
                            shape = RoundedCornerShape(16.dp)
                        )
                )

                ReusableSpacer(8.dp)

                Column {
                    RFText(
                        text = stringResource(R.string.business_goal),
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 12.sp,
                            color = RFColor.UxComponentColorCharcoal
                        )
                    )

                    RFText(
                        text = stringResource(R.string.percentage_format, uiState.commercialGoal.toString()) ,
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 18.sp,
                            color = RFColor.UxComponentColorIrisBlue
                        )
                    )
                }

            }
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(start = 16.dp)
        ) {
            CircleGraph(
                approvedLine = uiState.data?.lineCred.toDoubleOrDefault(),
                availableBalance = uiState.data?.balance?.toNumericValue()!!.toDouble(),
                commercialGoal = uiState.commercialGoal!!.toInt(),
            )
        }


    }

}

@Composable
private fun HeaderHomeSection() {
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
                    text = stringResource(R.string.welcome_user, UserSession.getUserData(UserSession.NAME)),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 20.sp,
                        color = RFColor.UxComponentColorWhite
                    )
                )
            }

            ReusableSpacer(4.dp)

            RFText(
                text = UserSession.getUserData(UserSession.BUSINESS_NAME),
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

@Preview(showBackground = true)
@Composable
fun DefaultHomeScreenPreview() {
    IndexManagerScreen()
}