package cat.monti.tasques

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
class ManegadorAutenticacio (private val context: Context) {

    private val tag = "MANEGADOR_AUTENTIFICACIO"
    private val ID_CLIENT = ""

    private val autentificacio: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val manegadorDeCredencials = CredentialManager.create(context)


    /*val googleIdOption = GetGoogleIdOption.Builder()
        // Your server's client ID, not your Android client ID.
        .setServerClientId(ID_CLIENT)
        .setFilterByAuthorizedAccounts(false)
        .build()

    // Create the Credential Manager request
    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()*/

    fun obtenirUsuariActual(): FirebaseUser? {
        return autentificacio.currentUser
    }

    suspend fun tancaSessio() {
        manegadorDeCredencials.clearCredentialState(ClearCredentialStateRequest())
        autentificacio.signOut()
    }

    fun obtenUsuariActual(): FirebaseUser?{
        return autentificacio.currentUser
    }

    fun hiHaUsuariIniciat() =obtenUsuariActual() != null


    private suspend fun creaPeticioDeCredencials(): GetCredentialResponse
    {
        val peticio = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false) 
                    .setServerClientId(ID_CLIENT)
                    .setAutoSelectEnabled(false) //true si volem que seleccioni automàticament el compte principal
                    .build()
            )
            .build()
        return manegadorDeCredencials.getCredential(
            request = peticio,
            context = context
        )
    }

    private suspend fun manegaIniciDeSessio(resultat: GetCredentialResponse): Boolean {
        val credencial = resultat.credential
        if (credencial is CustomCredential &&
            credencial.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL)
        {
            try{
                val token = GoogleIdTokenCredential.createFrom(credencial.data)
                Log.i(tag, "Token: ${token.displayName}")
                Log.i(tag, "Token: ${token.familyName}")
                Log.i(tag, "Token: ${token.phoneNumber}")
                Log.i(tag, "Token: ${token.profilePictureUri}")

                val credencialDeGoogle = GoogleAuthProvider.getCredential(token.idToken, null)
                val resultatDeAutenticacio = autentificacio.signInWithCredential(credencialDeGoogle).await()

                return resultatDeAutenticacio.user != null
            }
            catch (e: GoogleIdTokenParsingException)
            {
                Log.e(tag, "Error al parsejar el token: ${e.message}")
                return false
            }

        }
        return false;
    }

}
