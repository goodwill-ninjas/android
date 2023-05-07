package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

sealed class Screen(val route: String) {
    object AddDonationFirstScreen : Screen("Add_donation")
    object AdvancedDonationParams : Screen("Add_donation_advanced")
    object AddDisqualification : Screen("Add_disqualification")
    object AddDisqualificationAdvanced : Screen("Add_disqualification_advanced")
    object Journal : Screen("Journal")
    object BottomModal : Screen("BottomModal")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}




