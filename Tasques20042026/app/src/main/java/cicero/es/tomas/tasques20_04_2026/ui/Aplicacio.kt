package cat.monti.tasques.ui

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cat.monti.tasques.ManegadorAutenticacio
import cicero.es.tomas.tasques20_04_2026.R
import cat.monti.tasques.navegacio.DestinacioPerfil
import cat.monti.tasques.navegacio.DestinacioPortada
import cat.monti.tasques.navegacio.GrafDeNavegacio
import cat.monti.tasques.navegacio.opcionsDrawer
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CalaixDeNavegacio(
    controladorDeNavegacio: NavHostController = rememberNavController(),
    ambitCorrutina: CoroutineScope = rememberCoroutineScope(),
    estatDrawer: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    rutaActual: NavBackStackEntry?,
    destinacioActual: NavDestination?,
    manegadorAutenticacio: ManegadorAutenticacio
) {
    val usuariAutenticat: FirebaseUser? = manegadorAutenticacio.obtenUsuariActual()
    val context = LocalContext.current


    ModalNavigationDrawer(
        drawerState = estatDrawer,
        drawerContent = {
            ModalDrawerSheet (
                drawerShape = ShapeDefaults.Small, //fa referència a la mida del corner radius
                drawerContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                drawerContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                drawerTonalElevation = 5.dp,
                windowInsets = WindowInsets(left = 24.dp, right = 24.dp, top = 48.dp) // És el padding
            ){
                Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().aspectRatio(3F),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer)
                Spacer (Modifier.height(48.dp))
                Divider(
                    modifier = Modifier.height(15.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer (Modifier.height(48.dp))
                opcionsDrawer.forEach {opcio->
                    NavigationDrawerItem  (
                        label = { Text(opcio.titol) },
                        selected = destinacioActual?.hierarchy?.any {it.hasRoute(opcio.ruta::class) } == true,
                        icon = {if (destinacioActual?.hierarchy?.any {it.hasRoute(opcio.ruta::class) } == true)
                            Icon(imageVector = opcio.iconaSeleccionada, contentDescription = opcio.titol)
                        else
                            Icon(imageVector = opcio.iconaNoSeleccionada, contentDescription = opcio.titol)
                        },
                        onClick = {
                                ambitCorrutina.launch {
                                    estatDrawer.close()
                                }
                                var destinacio = opcio.ruta
                                Log.e("DEBUG", "Navego a ${destinacio.toString()}")
                                controladorDeNavegacio.navigate(destinacio)
                            },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedBadgeColor = MaterialTheme.colorScheme.error,
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            selectedBadgeColor = MaterialTheme.colorScheme.error,
                            selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer),
                        badge = {
                            if (opcio.mostraInsignia)
                                Icon(imageVector = opcio.IconaInsignia, "Opció destacada")
                        },
                        shape = ShapeDefaults.Medium
                    )
                }
            }
        },
        gesturesEnabled = esPotObrirElDrawer(destinacioActual)
    ) {
        Bastida(
            rutaActual = rutaActual,
            destinacioActual = destinacioActual,
            controladorDeNavegacio = controladorDeNavegacio,
            ambitCorrutina = ambitCorrutina,
            estatDrawer = estatDrawer,
            manegadorAutentificacio = manegadorAutenticacio
        )
    }
}


private fun esPotObrirElDrawer(destinacioActual: NavDestination?) = listOf(
    DestinacioPortada::class,
    DestinacioPerfil::class,
    ).any { destinacioActual?.hasRoute(it) ?: true }

private fun esPotAnarEnrera(destinacioActual: NavDestination?) = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bastida(
    rutaActual: NavBackStackEntry?,
    destinacioActual: NavDestination?,
    controladorDeNavegacio: NavHostController,
    ambitCorrutina: CoroutineScope,
    estatDrawer: DrawerState,
    manegadorAutentificacio: ManegadorAutenticacio
)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasques") },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    if(esPotObrirElDrawer(destinacioActual)) {
                        IconButton(onClick = {
                            ambitCorrutina.launch {
                                if (estatDrawer.isClosed)
                                    estatDrawer.open()
                                else
                                    estatDrawer.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    else if (esPotAnarEnrera(destinacioActual)){
                        IconButton(onClick = {
                            controladorDeNavegacio.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    else {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Login,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                })
        }

    )
    {paddingValues ->
        GrafDeNavegacio(
            controladorDeNavegacio = controladorDeNavegacio,
            manegadorAutenticacio = manegadorAutentificacio,
            paddingValues = paddingValues
        )
    }
}