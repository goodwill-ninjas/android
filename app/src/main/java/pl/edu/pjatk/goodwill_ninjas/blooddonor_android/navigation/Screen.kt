package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

sealed class Screen (val route: String) {
    object MainPage : Screen(Routes.SELF)
    object Journal : Screen(Routes.JOURNAL)
    object Login: Screen(Routes.LOGIN)
}
