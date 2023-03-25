package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
<<<<<<< HEAD
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal.DonationJournal
=======
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.BottomSheetDialog
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.DatePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.DonationType
>>>>>>> 26d5110 (Add date utils)
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.MainPage
import java.time.LocalDateTime

object Routes {
<<<<<<< HEAD
    const val SELF = "Main"
    const val JOURNAL = "Journal"
}
=======
    val SELF = "Main"
    val ADD_DONATION = "Add_donation"
    val BOTTOM_SHEET_DIALOG = "Bottom_dialog"
    }
>>>>>>> 26d5110 (Add date utils)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
<<<<<<< HEAD
fun Navigation() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val donations = listOf(
        Donation("Krew pełna", 450, LocalDateTime.of(2023, 2, 3, 0, 0)),
        Donation("Krew pełna", 450, LocalDateTime.of(2022, 11, 16, 0, 0)),
        Donation("Krew pełna", 450, LocalDateTime.of(2021, 2, 3, 0, 0)),
        Donation("Krew pełna", 450, LocalDateTime.of(2020, 7, 3, 0, 0)),
        Donation("Krew pełna", 450, LocalDateTime.of(2019, 10, 3, 0, 0)),
        Donation("Krew pełna", 450, LocalDateTime.of(2018, 12, 3, 0, 0)),
        Donation("Krew pełna", 450, LocalDateTime.of(2018, 2, 3, 0, 0)),
        Donation("Krew pełna", 450, LocalDateTime.of(2017, 10, 16, 0, 0)),
    )
    val name = "Android"
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MytopBar(name) },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.Journal.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        NavHost(navController = navController, startDestination = Routes.SELF) {
            composable(route = Routes.SELF) {
                MainPage(name = name)
            }
            composable(route = Routes.JOURNAL) {
                DonationJournal(name, donations)
            }
=======
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.SELF) {
        composable(route = Routes.SELF) {
            MainPage(
                name = "Android",
                bloodDonation = NextDonation(
                    LocalDateTime.of(2023, 2, 23, 0, 0),
                    DonationType.FULL
                ),
                navHostController
            )
        }
//        composable(route = Routes.ADD_DONATION) {
//            DatePicker()
//        }
        composable(route = Routes.BOTTOM_SHEET_DIALOG) {
            BottomSheetDialog()
>>>>>>> 26d5110 (Add date utils)
        }
    }
}