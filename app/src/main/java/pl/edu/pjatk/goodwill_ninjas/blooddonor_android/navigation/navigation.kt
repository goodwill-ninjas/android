package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import org.joda.time.DateTime
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.DonationType
import java.time.LocalDateTime
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.MainPage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.NextDonation
import java.time.format.DateTimeFormatter
import java.util.*

object Routes {
    val SELF = "Main"
}

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.SELF) {
        composable(route = Routes.SELF) {
            MainPage(name = "Android", bloodDonation = NextDonation(LocalDateTime.of(2023, 2, 23, 0, 0), DonationType.FULL), navHostController)
        }
    }
}