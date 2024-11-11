package com.repsol.home.ui.home

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.home.data.movie.remote.dto.response.MovieResponse
import com.repsol.rf_assets.R
import kotlinx.coroutines.launch
import com.repsol.home.ui.home.interactor.HomeUiState as UiState


enum class BottomBarContent {
    INICIO, VEHICULOS, TARJETAS, CONDUCTORES, NONE
}

enum class DrawerOnlyContent {
    TRACKING, REPORTES, MEDIOS_PAGO, CONFIGURACIONES // Opciones exclusivas del Drawer
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardManagerScreen() = Stateful<HomeViewModel> {
    val uiState by uiState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    // Estados de selección para BottomAppBar y Drawer
    var selectedContent by remember { mutableStateOf(BottomBarContent.INICIO) }
    var selectedDrawerOnlyContent by remember { mutableStateOf<DrawerOnlyContent?>(null) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets.captionBar
            ) {
                CustomDrawerContent(
                    selectedContent = selectedContent,
                    selectedDrawerOnlyContent = selectedDrawerOnlyContent,
                    onOptionSelected = { route ->
                        when (route) {
                            "vehiculos" -> {
                                selectedContent = BottomBarContent.VEHICULOS
                                selectedDrawerOnlyContent = null
                                navController.navigate("vehiculos")
                            }

                            "tarjetas" -> {
                                selectedContent = BottomBarContent.TARJETAS
                                selectedDrawerOnlyContent = null
                                navController.navigate("tarjetas")
                            }

                            "conductores" -> {
                                selectedContent = BottomBarContent.CONDUCTORES
                                selectedDrawerOnlyContent = null
                                navController.navigate("conductores")
                            }

                            "tracking" -> {
                                selectedDrawerOnlyContent = DrawerOnlyContent.TRACKING
                                selectedContent = BottomBarContent.NONE
                                navController.navigate("tracking")
                            }

                            "reportes" -> {
                                selectedDrawerOnlyContent = DrawerOnlyContent.REPORTES
                                selectedContent = BottomBarContent.NONE
                                navController.navigate("reportes")
                            }

                            "medios_pago" -> {
                                selectedDrawerOnlyContent = DrawerOnlyContent.MEDIOS_PAGO
                                selectedContent = BottomBarContent.NONE
                                navController.navigate("medios_pago")
                            }

                            "configuracion" -> {
                                selectedDrawerOnlyContent = DrawerOnlyContent.CONFIGURACIONES
                                selectedContent = BottomBarContent.NONE
                                navController.navigate("configuracion")
                            }
                        }
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
                                    selectedContent = BottomBarContent.INICIO
                                    selectedDrawerOnlyContent =
                                        null // Limpiar opciones exclusivas del Drawer
                                    navController.navigate("inicio")
                                },
                                enabled = selectedContent != BottomBarContent.INICIO,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home",
                                    tint = if (selectedContent == BottomBarContent.INICIO) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = {
                                    selectedContent = BottomBarContent.VEHICULOS
                                    selectedDrawerOnlyContent = null
                                    navController.navigate("vehiculos")
                                },
                                enabled = selectedContent != BottomBarContent.VEHICULOS,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "Vehiculos",
                                    tint = if (selectedContent == BottomBarContent.VEHICULOS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = {
                                    selectedContent = BottomBarContent.TARJETAS
                                    selectedDrawerOnlyContent = null
                                    navController.navigate("tarjetas")
                                },
                                enabled = selectedContent != BottomBarContent.TARJETAS,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Tarjetas",
                                    tint = if (selectedContent == BottomBarContent.TARJETAS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = {
                                    selectedContent = BottomBarContent.CONDUCTORES
                                    selectedDrawerOnlyContent = null
                                    navController.navigate("conductores")
                                },
                                enabled = selectedContent != BottomBarContent.CONDUCTORES,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Conductores",
                                    tint = if (selectedContent == BottomBarContent.CONDUCTORES) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
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
    selectedContent: BottomBarContent?,
    selectedDrawerOnlyContent: DrawerOnlyContent?,
    onOptionSelected: (String) -> Unit,
    onLogoutClicked: () -> Unit,
    onCloseDrawer: () -> Unit
) {
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
                isSelected = selectedContent == BottomBarContent.TARJETAS
            ) { onOptionSelected("tarjetas") }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.Person,
                label = "Conductores",
                isSelected = selectedContent == BottomBarContent.CONDUCTORES
            ) { onOptionSelected("conductores") }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.ShoppingCart,
                label = "Vehículos",
                isSelected = selectedContent == BottomBarContent.VEHICULOS
            ) { onOptionSelected("vehiculos") }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.LocationOn,
                label = "Tracking",
                isSelected = selectedDrawerOnlyContent == DrawerOnlyContent.TRACKING
            ) { onOptionSelected("tracking") }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.Add,
                label = "Reportes",
                isSelected = selectedDrawerOnlyContent == DrawerOnlyContent.REPORTES
            ) { onOptionSelected("reportes") }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.PlayArrow,
                label = "Medios de pago",
                isSelected = selectedDrawerOnlyContent == DrawerOnlyContent.MEDIOS_PAGO
            ) { onOptionSelected("medios_pago") }

            HorizontalDivider(
                Modifier.padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            DrawerOption(
                icon = Icons.Default.Settings,
                label = "Configuración",
                isSelected = selectedDrawerOnlyContent == DrawerOnlyContent.CONFIGURACIONES
            ) { onOptionSelected("configuracion") }

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
        composable("inicio") { HomeManagerScreen(Modifier.padding(paddingValues)) }
        composable("vehiculos") { VehiculosScreen(Modifier.padding(paddingValues)) }
        composable("tarjetas") { TarjetasScreen(Modifier.padding(paddingValues)) }
        composable("conductores") { ConductoresScreen(Modifier.padding(paddingValues)) }
        composable("tracking") { TrackingScreen(Modifier.padding(paddingValues)) }
        composable("reportes") { ReportesScreen(Modifier.padding(paddingValues)) }
        composable("medios_pago") { MediosPagoScreen(Modifier.padding(paddingValues)) }
        composable("configuracion") { ConfiguracionesScreen(Modifier.padding(paddingValues)) }
    }
}



@Composable
fun VehiculosScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(16.dp)
    ) {
        Text("Vehiculos Screen")
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
        uiState = UiState(
            movies = listOf(
                MovieResponse(
                    title = "Batman",
                    overview = "The Dark Knight",
                    posterPath = "/path/to/image",
                    backdropPath = "/path/to/image",
                    releaseDate = "2021-10-10",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    id = 1,
                    originalLanguage = "en",
                    originalTitle = "Batman",
                    popularity = 100.0,
                    video = false,
                    adult = false,
                    genreIds = listOf(1, 2, 3)
                )
            )
        )
    ) {

    }
}