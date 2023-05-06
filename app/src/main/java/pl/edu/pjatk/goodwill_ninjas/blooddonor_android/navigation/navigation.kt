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
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal.DonationJournal
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.MainPage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationState

object Routes {
    const val SELF = "Main"
    const val JOURNAL = "Journal"
    val ADD_DONATION = "Add_donation"
    val BOTTOM_SHEET_DIALOG = "Bottom_dialog"
    val ADD_DISQUALIFICATION = "Add_disqualification"
    val ADD_DISCQUALIFICATION_ADVANCED = "Add_disqualification_advanced"
    val BottomModal = "BottomModal"
    val ADVANCED = "Advanced_screen"
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation(
    state: DonationState,
    onEvent: (DonationEvent) -> Unit
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState()
    val name = "Android"
    val alert: () -> Unit = {
        Log.d("debugTag", "Value" + currentRoute)
    }
    alert()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MytopBar(name) },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (currentRoute != "BottomModal") {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.BottomModal.route)
                Log.i("message", "FAB starting page")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }}
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if (currentRoute != "BottomModal") {
                MyBottomBar(navController)
            }
        },
    ) {
        NavHost(navController, startDestination = Routes.SELF) {
            composable(route = Routes.SELF) {
                MainPage(name = name)
            }
            composable(route = Routes.JOURNAL) {
                DonationJournal(name, state, onEvent)
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
            composable(route = Routes.BottomModal) {
                BottomModal(navController)
            }
        }
    }
}