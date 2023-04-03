package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

sealed class Screen(val route: String) {
    object MainPage : Screen("Main")
    object DatePicker : Screen("Add_donation")
    object BottomSheetDialog : Screen("Bottom_dialog")
    object AddDonationFirstScreen: Screen("Add_donation")
    object AdvancedDonationParams : Screen("Advanced_params")

    object AddDisqualification : Screen("Add_disqualification")

    object AddDisqualificationAdvanced: Screen("add_disqualification_advanced")



    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}