package co.uts.inmoapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import co.uts.inmoapp.ui.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LogInViewModel : ViewModel() {
    var correo by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisibility by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    private val auth = FirebaseAuth.getInstance()

    fun loginWithEmailAndPassword(context: Context, navController: NavController) {
        if (correo.isNotBlank() && password.isNotBlank()) {
            viewModelScope.launch {
                isLoading = true
                try {
                    auth.signInWithEmailAndPassword(correo, password).await()
                    isLoading = false
                    navController.popBackStack()
                    navController.navigate(AppScreens.Main.route)
                } catch (e: FirebaseAuthException) {
                    when (e) {
                        is FirebaseAuthInvalidCredentialsException -> Toast.makeText(
                            context, "El correo ingresado no es valido", Toast.LENGTH_SHORT
                        ).show()
                        else -> Toast.makeText(
                            context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT
                        ).show()
                    }
                    Log.d("FirebaseAuth", e.toString())
                }
                isLoading = false
            }
        } else {
            Toast.makeText(context, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun goToRegister(navController: NavController) {
        navController.popBackStack()
        navController.navigate(AppScreens.Signin.route)
    }
}