package com.repsol.gestor_dashboard.ui.dashboard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.gestor_dashboard.ui.NavigationHost
import com.repsol.gestor_dashboard.ui.dashboard.interactor.BottomBarContent
import com.repsol.gestor_dashboard.ui.dashboard.interactor.DrawerOnlyContent
import com.repsol.rf_assets.R
import com.repsol.tools.utils.UserSession
import kotlinx.coroutines.launch
import com.repsol.gestor_dashboard.ui.dashboard.interactor.HomeUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.dashboard.interactor.HomeUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.dashboard.interactor.HomeUiState as UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestorDashboardScreen() = Stateful<DashboardViewModel> {
    val uiState by uiState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    OnUiEvent {
        when (it) {
            is UiEvent.GoToContent -> {
                navController.navigate(it.route)
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets.captionBar
            ) {
                CustomDrawerContent(
                    onOptionSelected = { content, drawer ->
                        execUiIntent(UiIntent.UpdateContent(content, drawer))
                        scope.launch { drawerState.close() }
                    },
                    onLogoutClicked = {
                        // Acción de cierre de sesión
                    },
                    onCloseDrawer = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
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
                                    execUiIntent(UiIntent.UpdateContent(BottomBarContent.VEHICULOS))
                                },
                                enabled = uiState.selectedContent != BottomBarContent.VEHICULOS,
                                modifier = Modifier.weight(1f)
                            ) {

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    RFIcon(
                                        modifier = Modifier.size(24.dp),
                                        painter = if (uiState.selectedContent == BottomBarContent.VEHICULOS) painterResource(
                                            R.drawable.ic_camion_filled
                                        ) else painterResource(R.drawable.ic_camion_ouline),
                                        tint = if (uiState.selectedContent == BottomBarContent.VEHICULOS) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                    )

                                    RFText(
                                        text = stringResource(R.string.vehicles_dashboard),
                                        textStyle = RFTextStyle.Repsol(
                                            fontSize = 12.sp,
                                            color = if (uiState.selectedContent == BottomBarContent.VEHICULOS) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                        )
                                    )
                                }

                            }

                            IconButton(
                                onClick = {
                                    execUiIntent(UiIntent.UpdateContent(BottomBarContent.TARJETAS))
                                },
                                enabled = uiState.selectedContent != BottomBarContent.TARJETAS,
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    RFIcon(
                                        modifier = Modifier.size(24.dp),
                                        painter = if (uiState.selectedContent == BottomBarContent.TARJETAS) painterResource(
                                            R.drawable.ic_card_filled
                                        ) else painterResource(R.drawable.ic_card_ouline),
                                        tint = if (uiState.selectedContent == BottomBarContent.TARJETAS) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                    )

                                    RFText(
                                        text = stringResource(R.string.cards_dashboard),
                                        textStyle = RFTextStyle.Repsol(
                                            fontSize = 12.sp,
                                            color = if (uiState.selectedContent == BottomBarContent.TARJETAS) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                        )
                                    )
                                }
                            }

                            IconButton(
                                onClick = {
                                    execUiIntent(UiIntent.UpdateContent(BottomBarContent.CONDUCTORES))
                                },
                                enabled = uiState.selectedContent != BottomBarContent.CONDUCTORES,
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    RFIcon(
                                        modifier = Modifier.size(24.dp),
                                        painter = if (uiState.selectedContent == BottomBarContent.CONDUCTORES) painterResource(
                                            R.drawable.ic_user_filled
                                        ) else painterResource(R.drawable.ic_user_ouline),
                                        tint = if (uiState.selectedContent == BottomBarContent.CONDUCTORES) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                    )

                                    RFText(
                                        text = stringResource(R.string.driver_dashboard),
                                        textStyle = RFTextStyle.Repsol(
                                            fontSize = 12.sp,
                                            color = if (uiState.selectedContent == BottomBarContent.CONDUCTORES) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorCharcoal
                                        )
                                    )
                                }

                            }

                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() } // Abre el Drawer sin cambiar la selección
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    RFIcon(
                                        painter = painterResource(R.drawable.ic_menu_filled),
                                        tint =  RFColor.UxComponentColorCharcoal
                                    )

                                    RFText(
                                        text = stringResource(R.string.menu_dashboard),
                                        textStyle = RFTextStyle.Repsol(
                                            fontSize = 12.sp,
                                            color = RFColor.UxComponentColorCharcoal
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
}

@Composable
fun CustomDrawerContent(
    onOptionSelected: (BottomBarContent, DrawerOnlyContent?) -> Unit,
    onLogoutClicked: () -> Unit,
    onCloseDrawer: () -> Unit,
) = ChildStateful<DashboardViewModel> {
    val uiState by uiState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(250.dp)
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
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

            IconButton(
                onClick = onCloseDrawer,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp)
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_close),
                    tint = RFColor.UxComponentColorWhite
                )
            }

            Column(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_rocket), // Reemplaza con tu imagen de perfil
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Transparent)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = UserSession.getFullName(),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = UserSession.getUserData(UserSession.BUSINESS_NAME),
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        HorizontalDivider(
            Modifier.padding(horizontal = 24.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )

        Column(modifier = Modifier.weight(1f)) {
            DrawerOption(
                icon = R.drawable.ic_card_ouline,
                label = stringResource(R.string.cards_dashboard),
                isSelected = uiState.selectedContent == BottomBarContent.TARJETAS
            ) { onOptionSelected(BottomBarContent.TARJETAS, null) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = R.drawable.ic_user_ouline,
                label = stringResource(R.string.driver_dashboard),
                isSelected = uiState.selectedContent == BottomBarContent.CONDUCTORES
            ) { onOptionSelected(BottomBarContent.CONDUCTORES, null) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = R.drawable.ic_camion_ouline,
                label = stringResource(R.string.vehicles_dashboard),
                isSelected = uiState.selectedContent == BottomBarContent.VEHICULOS
            ) { onOptionSelected(BottomBarContent.VEHICULOS, null) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = R.drawable.ic_camion_ouline,
                label = stringResource(R.string.tracking_menu),
                isSelected = uiState.selectedDrawerOnlyContent == DrawerOnlyContent.TRACKING
            ) { onOptionSelected(BottomBarContent.NONE, DrawerOnlyContent.TRACKING) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = R.drawable.ic_report_ouline,
                label = stringResource(R.string.report_menu),
                isSelected = uiState.selectedDrawerOnlyContent == DrawerOnlyContent.REPORTES
            ) { onOptionSelected(BottomBarContent.NONE, DrawerOnlyContent.REPORTES) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = R.drawable.ic_saving_ouline,
                label = stringResource(R.string.pay_menu),
                isSelected = uiState.selectedDrawerOnlyContent == DrawerOnlyContent.MEDIOS_PAGO
            ) { onOptionSelected(BottomBarContent.NONE, DrawerOnlyContent.MEDIOS_PAGO) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = R.drawable.ic_settings_outline,
                label = stringResource(R.string.settings_menu),
                isSelected = uiState.selectedDrawerOnlyContent == DrawerOnlyContent.CONFIGURACIONES
            ) { onOptionSelected(BottomBarContent.NONE, DrawerOnlyContent.CONFIGURACIONES) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )
        }

        // Botón de Cerrar Sesión
        Spacer(modifier = Modifier.height(16.dp))

        Column(Modifier.windowInsetsPadding(WindowInsets.navigationBars)) {
            DrawerOption(
                icon = R.drawable.ic_direction_outline,
                label = stringResource(R.string.logout_menu),
                isSelected = false
            ) { onLogoutClicked() }

        }
    }
}

@Composable
fun DrawerOption(
    @DrawableRes icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (isSelected) RFColor.UxComponentColorSafetyOrange else RFColor.UxComponentColorGrey
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RFIcon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(icon),
            tint = textColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        RFText(
            text = label,
            textStyle = RFTextStyle.Roboto(
                fontSize = 14.sp,
                color = textColor
            )
        )
    }
}

@Composable
fun TarjetasScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Green)
            .padding(16.dp)
    ) {
        Text("Tarjetas Screen")
    }
}

@Composable
fun ConductoresScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
            .padding(16.dp)
    ) {
        Text("Conductores Screen")
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

@Composable
fun ReportesScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(16.dp)
    ) {
        Text("Reportes Screen")
    }
}

@Composable
fun MediosPagoScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(16.dp)
    ) {
        Text("MediosPago Screen")
    }
}

@Composable
fun ConfiguracionesScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        Text("Configuraciones Screen")
    }
}

@Preview
@Composable
fun DefaultDashboardManagerScreenPreview() {
    ScreenPreview(
        uiState = UiState()
    ) {
        GestorDashboardScreen()
    }
}