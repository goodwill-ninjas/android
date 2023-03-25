package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

sealed class Screen (val route: String) {
<<<<<<< HEAD
    object MainPage : Screen(Routes.SELF)
    object Journal : Screen(Routes.JOURNAL)
}
=======
    object MainPage: Screen("main")
    object DatePicker: Screen("Add_donation")
    object BottomSheetDialog: Screen("Bottom_dialog")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
>>>>>>> 26d5110 (Add date utils)
