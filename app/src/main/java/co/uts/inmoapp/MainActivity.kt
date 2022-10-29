package co.uts.inmoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.uts.inmoapp.ui.navigation.AppScreens
import co.uts.inmoapp.ui.theme.InmoAppTheme
import co.uts.inmoapp.view.LoginView
import co.uts.inmoapp.view.SigninView
import co.uts.inmoapp.view.main.MainView
import co.uts.inmoapp.view.main.RentItem
import co.uts.inmoapp.viewmodel.LogInViewModel
import co.uts.inmoapp.viewmodel.SignInViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InmoAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = AppScreens.Login.route) {
                    composable(AppScreens.Login.route) {
                        LoginView(viewModel = LogInViewModel(), navController = navController)
                    }
                    composable(AppScreens.Main.route) {
                        MainView(navController = navController)
                    }
                    composable(AppScreens.Signin.route){
                        SigninView(viewModel = SignInViewModel(), navController = navController)
                    }
                    composable(AppScreens.RentItem.route){
                        RentItem()
                    }
                }
            }
        }
    }
}