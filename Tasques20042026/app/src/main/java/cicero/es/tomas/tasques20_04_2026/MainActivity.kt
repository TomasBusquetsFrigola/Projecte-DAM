package cicero.es.tomas.tasques20_04_2026

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cicero.es.tomas.tasques20_04_2026.ui.theme.Tasques20042026Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lateinit var auth: FirebaseAuth
//        auth = Firebase.auth
        enableEdgeToEdge()
        setContent {
            Tasques20042026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    PantallaLogin(
//                        modifier = Modifier.padding(innerPadding),
//                        auth = auth
//                    )
                }
            }
        }
    }
}

@Composable
fun PantallaLogin (
    modifier: Modifier = Modifier,
    auth: FirebaseAuth
) {
    var coroutine = rememberCoroutineScope()

    var currentUser by remember { mutableStateOf(auth.currentUser) }



    Column(
        modifier = modifier
    ) {
        if (currentUser == null) {
            Text("Sense usuari")
        } else {
            Text(
                "ID USUARI ACTUAL: ${currentUser!!.uid}" +
                        "NOM: ${currentUser!!.displayName}" +
                        "ÉS ANONIM: ${currentUser!!.isAnonymous}"
            )
        }
        Button(
            onClick = {
                try {
                    coroutine.launch {
                        var result = auth.signInAnonymously().await()
                        Log.e("DEBUG", "LOGIN REALITZAT CORRECTAMENT")
                    }
                } catch (e: Exception){
                    Log.e("DEBUG", "LOGIN REALITZAT INCORRECTAMENT: ${e.message}")
                }
            }
        ) {
            Text("Login anonim")
        }
    }
}