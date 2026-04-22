package cat.monti.tasques.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.monti.tasques.ManegadorAutenticacio
import cat.monti.tasques.R
import kotlinx.coroutines.launch

@Composable
fun PantallaLogin(
    mAutenticacio:ManegadorAutenticacio,
    modifier: Modifier = Modifier,
    navegaAInici: () -> Unit,
    navegaARegistre: () -> Unit
){
    val ambitCorrutina = rememberCoroutineScope()
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false)}
    var missatgeError by remember { mutableStateOf("")}

    Box(modifier = Modifier.padding(16.dp).fillMaxSize()){
        Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id= R.drawable.ic_launcher_foreground),
                contentDescription = "firebase"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Firebase per a Android",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
            }, modifier=Modifier.fillMaxWidth()) {
                Text("Inici sessió com anònim")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.height(2.dp))
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                label = { Text("Correu") },
                value = emailText,
                onValueChange = { emailText = it },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                label = { Text("Password") },
                value = passwordText,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { passwordText = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                ambitCorrutina.launch {
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Inici sessió amb email")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.height(2.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Inici sessió amb Google")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.height(2.dp))
            Spacer(modifier = Modifier.height(10.dp))

            ClickableText(
                text = AnnotatedString("No tens compte? Registra't aquí" ),
                style = TextStyle.Default.copy(
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary)
            ) {
                navegaARegistre()
            }
            
            if (error){
                Text(missatgeError, color = Color(0xFFFF0000))
            }

        }



    }
}