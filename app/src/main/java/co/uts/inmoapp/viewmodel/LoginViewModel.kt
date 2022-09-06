package co.uts.inmoapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import co.uts.inmoapp.ui.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {
    var correo by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisibility by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    private val auth = FirebaseAuth.getInstance()

    fun loginWithEmailAndPassword(context: Context,navController: NavController) {
        viewModelScope.launch {
            isLoading = true
            try {
                auth.signInWithEmailAndPassword(correo, password).await()
                isLoading = false
                navController.popBackStack()
                navController.navigate(AppScreens.Main.route)
            } catch (e:Exception){
                Toast.makeText(context,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            }
            isLoading = false
        }
    }
}