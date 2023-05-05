package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.SignInScreen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal.DonationJournal
import java.time.LocalDateTime

object Routes {
    const val SELF = "Main"
    const val JOURNAL = "Journal"
    const val LOGIN = "Login"
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
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
        bottomBar = {
            if (currentRoute != "Login") {

            MyBottomBar(navController)} },
        floatingActionButton = {
            if (currentRoute != "Login")
            FloatingActionButton(onClick = {
                navController.navigate(Screen.Journal.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },

        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        NavHost(navController = navController, startDestination = Routes.LOGIN) {
            composable(route = Routes.LOGIN) {
                SignInScreen()
            }
            composable(route = Routes.JOURNAL) {
                DonationJournal(name, donations)
            }
        }
    }
}