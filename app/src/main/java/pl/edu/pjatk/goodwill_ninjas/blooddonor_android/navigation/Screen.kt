package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

sealed class Screen (val route: String) {
    object MainPage : Screen(Routes.SELF)
    object Journal : Screen(Routes.JOURNAL)
    object  ADD_DONATION_FIRST_SCREEN: Screen(Routes.ADD_DONATION_FIRST_SCREEN)
    object AddDisqualification: Screen(Routes.AddDisqualification)
    object BottomSheetDialog: Screen(Routes.BottomSheetDialog)
    object AddDisqualificationAdvanced: Screen(Routes.AddDisqualificationAdvanced)
    object AdvancedDonationParams: Screen(Routes.AdvancedDonationParams)
}