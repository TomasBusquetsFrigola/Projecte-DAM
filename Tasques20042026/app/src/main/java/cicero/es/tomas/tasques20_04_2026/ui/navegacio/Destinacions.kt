package cat.monti.tasques.navegacio

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
//import androidx.compose.material.icons.outlined.SwitchAccount
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object DestinacioPortada

@Serializable
object DestinacioPerfil

@Serializable
object DestiLogin

@Serializable
object DestinacioRegistre

data class OpcioDrawer<T:Any>(val ruta:T,
                              val iconaNoSeleccionada: ImageVector,
                              val iconaSeleccionada: ImageVector,
                              val titol:String,
                              val mostraInsignia:Boolean = false,
                              val IconaInsignia: ImageVector = Icons.Default.Star)

val opcionsDrawer = listOf(
    OpcioDrawer(DestinacioPortada, Icons.Outlined.Home, Icons.Filled.Home, "Portada"),
//    OpcioDrawer(DestinacioPerfil, Icons.Outlined.SwitchAccount, Icons.Filled.SwitchAccount, "Perfil")
)