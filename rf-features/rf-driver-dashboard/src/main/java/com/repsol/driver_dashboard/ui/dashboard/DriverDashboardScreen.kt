package com.repsol.driver_dashboard.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.driver_dashboard.ui.NavigationHost
import com.repsol.driver_dashboard.ui.dashboard.interactor.BottomBarContent
import com.repsol.rf_assets.R
import com.repsol.driver_dashboard.ui.dashboard.interactor.HomeUiEvent as UiEvent
import com.repsol.driver_dashboard.ui.dashboard.interactor.HomeUiIntent as UiIntent
import com.repsol.driver_dashboard.ui.dashboard.interactor.HomeUiState as UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverDashboardScreen() = Stateful<DashboardViewModel> {
    val uiState by uiState()
    val navController = rememberNavController()

    OnUiEvent {
        when (it) {
            is UiEvent.GoToContent -> {
                navController.navigate(it.route)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 12.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_logo_repsol),
                            contentDescription = null
                        )

                        RFText(
                            text = stringResource(R.string.flotas),
                            textStyle = RFTextStyle.Repsol(
                                fontSize = 28.sp,
                                color = RFColor.UxComponentColorMidnightBlue
                            ),
                            textAlign = TextAlign.End
                        )
                    }

                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RFColor.UxComponentColorWhite.color,
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                content = {
                    Row {
                        IconButton(
                            onClick = {
                                execUiIntent(UiIntent.UpdateContent(BottomBarContent.INICIO))
                            },
                            enabled = uiState.selectedContent != BottomBarContent.INICIO,
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                RFIcon(
                                    modifier = Modifier.size(24.dp),
                                    painter = if (uiState.selectedContent == BottomBarContent.INICIO) painterResource(
                                        R.drawable.ic_home_filled
                                    ) else painterResource(R.drawable.ic_home_ouline),
                                    tint = if (uiState.selectedContent == BottomBarContent.INICIO) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                )

                                RFText(
                                    text = stringResource(R.string.start_dashboard),
                                    textStyle = RFTextStyle.Repsol(
                                        fontSize = 12.sp,
                                        color = if (uiState.selectedContent == BottomBarContent.INICIO) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                    )
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                execUiIntent(UiIntent.UpdateContent(BottomBarContent.MY_QR))
                            },
                            enabled = uiState.selectedContent != BottomBarContent.MY_QR,
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                RFIcon(
                                    modifier = Modifier.size(24.dp),
                                    painter = if (uiState.selectedContent == BottomBarContent.MY_QR) painterResource(
                                        R.drawable.ic_card_filled
                                    ) else painterResource(R.drawable.ic_card_ouline),
                                    tint = if (uiState.selectedContent == BottomBarContent.MY_QR) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                )

                                RFText(
                                    text = "Mi QR",
                                    textStyle = RFTextStyle.Repsol(
                                        fontSize = 12.sp,
                                        color = if (uiState.selectedContent == BottomBarContent.MY_QR) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                    )
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                execUiIntent(UiIntent.UpdateContent(BottomBarContent.TRACKING))
                            },
                            enabled = uiState.selectedContent != BottomBarContent.TRACKING,
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                RFIcon(
                                    modifier = Modifier.size(24.dp),
                                    painter = if (uiState.selectedContent == BottomBarContent.TRACKING) painterResource(
                                        R.drawable.ic_camion_filled
                                    ) else painterResource(R.drawable.ic_camion_ouline),
                                    tint = if (uiState.selectedContent == BottomBarContent.TRACKING) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                )

                                RFText(
                                    text = "Tracking",
                                    textStyle = RFTextStyle.Repsol(
                                        fontSize = 12.sp,
                                        color = if (uiState.selectedContent == BottomBarContent.TRACKING) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                    )
                                )
                            }
                        }
                    }
                },
                containerColor = RFColor.UxComponentColorWhite.color
            )
        }
    ) { paddingValues ->
        NavigationHost(navController = navController, paddingValues = paddingValues)
    }
}

@Composable
fun MyQrScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
            .padding(16.dp)
    ) {
        Text("My QR Screen")
    }
}

@Composable
fun TrackingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .padding(16.dp)
    ) {
        Text("Tracking Screen")
    }
}

@Preview
@Composable
fun DefaultDashboardManagerScreenPreview() {
    ScreenPreview(
        uiState = UiState()
    ) {

    }
}