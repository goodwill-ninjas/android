package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

sealed class Screen(val route: String) {
    object MainPage : Screen("main")
    object DatePicker : Screen("Add_donation")
    object BottomSheetDialog : Screen("Bottom_dialog")
    object AddDonationFirstScreen: Screen("Add_donation")
    object Advanced : Screen("Advanced_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
