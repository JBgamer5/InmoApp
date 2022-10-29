package co.uts.inmoapp.ui.navigation

sealed class AppScreens(val route: String) {
    object Signin : AppScreens("SigninScreen")
    object Login : AppScreens("LoginScreeen")
    object Main: AppScreens("MainScreen")
    object RentItem : AppScreens("RentItemScreen")
}
