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
import co.uts.inmoapp.data.FirebaseDatabase
import co.uts.inmoapp.model.Usuario
import co.uts.inmoapp.ui.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel : ViewModel() {

    var correo by mutableStateOf("")
    var nombre by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var password by mutableStateOf("")
    var repPasswrod by mutableStateOf("")
    var telefono by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    private val auth = FirebaseAuth.getInstance()

    fun signin(context: Context, navController: NavController) {
        if (nombre.isNotBlank() && apellidos.isNotBlank() && correo.isNotBlank() && password.isNotBlank() && repPasswrod.isNotBlank() && telefono.isNotBlank()) {
            if (repPasswrod == password) {
                val database = FirebaseDatabase()
                viewModelScope.launch {
                    isLoading = true
                    try {
                        auth.createUserWithEmailAndPassword(correo, password).await()
                        database.createUser(Usuario(nombre, apellidos, telefono), correo, password)
                        isLoading = false
                        Toast.makeText(
                            context, "El usuario ha sido registrado con exito", Toast.LENGTH_SHORT
                        ).show()
                        goToLogin(navController)
                    } catch (e: FirebaseAuthException) {
                        when (e) {
                            is FirebaseAuthWeakPasswordException -> Toast.makeText(
                                context,
                                "La contraseña es muy corta, minimo 6 caracteres",
                                Toast.LENGTH_SHORT
                            ).show()

                            is FirebaseAuthUserCollisionException ->Toast.makeText(
                                context,
                                "El correo ingresado ya se encuentra registrado",
                                Toast.LENGTH_SHORT
                            ).show()

                            is FirebaseAuthInvalidCredentialsException -> Toast.makeText(
                                context, "El correo ingresado no es valido", Toast.LENGTH_SHORT
                            ).show()
                        }
                        Log.d("FirebaseAuth", e.toString())
                        isLoading = false
                    }
                }
            } else {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun goToLogin(navController: NavController) {
        navController.popBackStack()
        navController.navigate(AppScreens.Login.route)
    }
}