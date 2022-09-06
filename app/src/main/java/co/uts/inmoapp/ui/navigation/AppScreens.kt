package co.uts.inmoapp.ui.navigation

sealed class AppScreens(val route: String) {
    object Login : AppScreens("LoginScreeen")
    object Main: AppScreens("MainScreen")
}
