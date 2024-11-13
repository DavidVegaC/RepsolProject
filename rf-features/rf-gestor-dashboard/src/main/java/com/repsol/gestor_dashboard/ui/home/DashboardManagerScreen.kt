package com.repsol.gestor_dashboard.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.gestor_dashboard.ui.home.interactor.BottomBarContent
import com.repsol.gestor_dashboard.ui.home.interactor.DrawerOnlyContent
import com.repsol.gestor_dashboard.ui.index.IndexManagerScreen
import com.repsol.gestor_dashboard.ui.vehicle.VehicleScreen
import com.repsol.rf_assets.R
import kotlinx.coroutines.launch
import com.repsol.gestor_dashboard.ui.home.interactor.HomeUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.home.interactor.HomeUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.home.interactor.HomeUiState as UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardManagerScreen() = Stateful<DashboardViewModel> {
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

                            Text(
                                text = "Flotas",
                                textAlign = TextAlign.End
                            )
                        }

                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary, // Color de fondo
                        titleContentColor = Color.White, // Color del título
                        actionIconContentColor = Color.White // Color de los íconos de acción
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
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home",
                                    tint = if (uiState.selectedContent == BottomBarContent.INICIO) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = {
                                    execUiIntent(UiIntent.UpdateContent(BottomBarContent.VEHICULOS))
                                },
                                enabled = uiState.selectedContent != BottomBarContent.VEHICULOS,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "Vehiculos",
                                    tint = if (uiState.selectedContent == BottomBarContent.VEHICULOS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = {
                                    execUiIntent(UiIntent.UpdateContent(BottomBarContent.TARJETAS))
                                },
                                enabled = uiState.selectedContent != BottomBarContent.TARJETAS,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Tarjetas",
                                    tint = if (uiState.selectedContent == BottomBarContent.TARJETAS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = {
                                    execUiIntent(UiIntent.UpdateContent(BottomBarContent.CONDUCTORES))
                                },
                                enabled = uiState.selectedContent != BottomBarContent.CONDUCTORES,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Conductores",
                                    tint = if (uiState.selectedContent == BottomBarContent.CONDUCTORES) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() } // Abre el Drawer sin cambiar la selección
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Más opciones"
                                )
                            }
                        }
                    }
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
        // Encabezado del Drawer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.drawer_eclipse_gradient), // Reemplaza con tu recurso de imagen
                contentDescription = null,
                contentScale = ContentScale.Crop,
                // Asegura que la imagen ocupe todo el espacio del Box
            )

            IconButton(
                onClick = onCloseDrawer,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp)
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar Drawer",
                    tint = Color.White
                )
            }

            Column(
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
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
                    text = "Alejandra Martínez Muñoz",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Logística Integral del Perú S.A.C.",
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
        // Opciones del Drawer
        Column(modifier = Modifier.weight(1f)) {
            DrawerOption(
                icon = Icons.Default.Star,
                label = "Tarjetas",
                isSelected = uiState.selectedContent == BottomBarContent.TARJETAS
            ) { onOptionSelected(BottomBarContent.TARJETAS, null) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.Person,
                label = "Conductores",
                isSelected = uiState.selectedContent == BottomBarContent.CONDUCTORES
            ) { onOptionSelected(BottomBarContent.CONDUCTORES, null) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.ShoppingCart,
                label = "Vehículos",
                isSelected = uiState.selectedContent == BottomBarContent.VEHICULOS
            ) { onOptionSelected(BottomBarContent.VEHICULOS, null) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.LocationOn,
                label = "Tracking",
                isSelected = uiState.selectedDrawerOnlyContent == DrawerOnlyContent.TRACKING
            ) { onOptionSelected(BottomBarContent.NONE, DrawerOnlyContent.TRACKING) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.Add,
                label = "Reportes",
                isSelected = uiState.selectedDrawerOnlyContent == DrawerOnlyContent.REPORTES
            ) { onOptionSelected(BottomBarContent.NONE, DrawerOnlyContent.REPORTES) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.PlayArrow,
                label = "Medios de pago",
                isSelected = uiState.selectedDrawerOnlyContent == DrawerOnlyContent.MEDIOS_PAGO
            ) { onOptionSelected(BottomBarContent.NONE, DrawerOnlyContent.MEDIOS_PAGO) }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.Settings,
                label = "Configuración",
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
                icon = Icons.Default.ExitToApp,
                label = "Cerrar sesión",
                isSelected = false // Cerrar sesión no necesita estado de selección
            ) { onLogoutClicked() }

        }
    }
}

@Composable
fun DrawerOption(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = textColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) { // cada composable seria un submodulo
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { IndexManagerScreen(Modifier.padding(paddingValues)) }
        composable("vehiculos") { VehicleScreen(Modifier.padding(paddingValues)) }
        composable("tarjetas") { TarjetasScreen(Modifier.padding(paddingValues)) }
        composable("conductores") { ConductoresScreen(Modifier.padding(paddingValues)) }
        composable("tracking") { TrackingScreen(Modifier.padding(paddingValues)) }
        composable("reportes") { ReportesScreen(Modifier.padding(paddingValues)) }
        composable("medios_pago") { MediosPagoScreen(Modifier.padding(paddingValues)) }
        composable("configuracion") { ConfiguracionesScreen(Modifier.padding(paddingValues)) }
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

    }
}