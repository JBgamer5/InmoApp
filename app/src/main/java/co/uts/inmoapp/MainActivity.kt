package co.uts.inmoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.uts.inmoapp.ui.navigation.AppScreens
import co.uts.inmoapp.ui.theme.InmoAppTheme
import co.uts.inmoapp.view.LoginView
import co.uts.inmoapp.view.main.MainScreen
import co.uts.inmoapp.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InmoAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = AppScreens.Login.route){
                    composable(AppScreens.Login.route){
                        LoginView(viewModel = LoginViewModel(), navController = navController)
                    }
                    composable(AppScreens.Main.route){
                        MainScreen()
                    }
                }
            }
        }
    }
}