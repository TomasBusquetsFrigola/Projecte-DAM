package cat.monti.tasques.navegacio

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.monti.tasques.ManegadorAutenticacio
import cat.monti.tasques.ui.PantallaLogin
import cat.monti.tasques.ui.PantallaPerfil
import cat.monti.tasques.ui.PantallaPortada

@Composable
fun GrafDeNavegacio(
    controladorDeNavegacio: NavHostController,
    manegadorAutenticacio: ManegadorAutenticacio,
    paddingValues: PaddingValues = PaddingValues(0.dp),){

    val usuari = manegadorAutenticacio.obtenirUsuariActual()

    val inici = if (usuari == null) DestiLogin else DestinacioPerfil

    NavHost(
        navController = controladorDeNavegacio,
        startDestination = inici,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<DestiLogin> {
            PantallaLogin(
                manegadorAutenticacio,
                navegaAInici = { controladorDeNavegacio.navigate(DestinacioPerfil) },
                navegaARegistre = {},
            )
        }
        composable<DestinacioPortada> {
            PantallaPortada()
        }

        composable<DestinacioPerfil> {
            PantallaPerfil(
                manegadorAutentificacio = manegadorAutenticacio,
                navegaALogin = { controladorDeNavegacio.navigate(DestiLogin) })
        }

        composable<DestinacioRegistre> {

        }
    }
}