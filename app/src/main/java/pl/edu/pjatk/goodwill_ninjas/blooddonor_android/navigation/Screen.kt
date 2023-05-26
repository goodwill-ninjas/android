package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

sealed class Screen(val route: String) {
    object MainPage : Screen(Routes.SELF)
    object Journal : Screen(Routes.JOURNAL)
    object Login : Screen(Routes.LOGIN)
    object AddDonationFirstScreen : Screen("Add_donation")
    object AdvancedDonationParams : Screen("Add_donation_advanced")
    object AddDisqualification : Screen("Add_disqualification")
    object AddDisqualificationAdvanced : Screen("Add_disqualification_advanced")
    object BottomModal : Screen("BottomModal")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }


}

