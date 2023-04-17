package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

import BottomModal
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDisqualification.AddDisqualification
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDisqualification.AddDisqualificationAdvanced
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.AdvancedDonationParams
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.BottomSheetDialog
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.WelcomeScreen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyFAB
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal.DonationJournal
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.MainPage
import java.time.LocalDateTime

object Routes {
    val SELF = "Main"
    val ADD_DONATION = "Add_donation"
    val BOTTOM_SHEET_DIALOG = "Bottom_dialog"
    val ADVANCED = "Advanced_params"
    val ADD_DISQUALIFICATION = "Add_disqualification"
    val ADD_DISCQUALIFICATION_ADVANCED = "Add_disqualification_advanced"
    val JOURNAL = "Journal"
    val BottomModal = "BottomModal"
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
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


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomBarAnimation() {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    when (navBackStackEntry?.destination?.route) {
        "BottomModal" -> {
            bottomBarState.value = false
            Log.i("bottomstate", "false")
        }
    }

    val scaffoldState = rememberScaffoldState()
//    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MytopBar("Android") },
        modifier = Modifier.fillMaxSize(),

        bottomBar = {

            if (currentRoute != "BottomModal") {

                MyBottomBar(navController)
            }
        },
        floatingActionButton = {
            if (currentRoute != "BottomModal") {
                FloatingActionButton(onClick = {
                    Log.i("straight from FAB", "main FAB")
//                    navController.navigate(Screen.BottomSheetDialog.route)
                    navController.navigate(Screen.BottomModal.route)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        NavHost(navController = navController, startDestination = Routes.SELF) {
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
            composable(route = Routes.SELF) {
                MainPage(name = name)
            }
            composable(route = Routes.JOURNAL) {
                DonationJournal(name, donations)
            }
            composable(route = Routes.ADD_DONATION) {
                WelcomeScreen(navController)

            }
            composable(route = Routes.BOTTOM_SHEET_DIALOG) {
                BottomSheetDialog(navController)

            }
            composable(route = Routes.ADVANCED) {
                AdvancedDonationParams(navController)
            }
            composable(route = Routes.ADD_DISQUALIFICATION) {
                AddDisqualification(navController)
            }
            composable(route = Routes.ADD_DISCQUALIFICATION_ADVANCED) {
                AddDisqualificationAdvanced(navController)

            }
            composable(route = Routes.BottomModal){
                BottomModal(navController)
            }

            }

        }
    }


