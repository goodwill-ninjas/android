package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.joda.time.DateTime
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal.DonationJournal
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.loginPage.SignInScreen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.MainPage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationState

object Routes {
    const val SELF = "Main"
    const val JOURNAL = "Journal"
    const val LOGIN = "Login"
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation(
    state: DonationState,
    onEvent: (DonationEvent) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
    val name = "Android"

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = { MytopBar(name) },
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            if (currentRoute != "Login") {

                MyBottomBar(navController)
            }
        },
        floatingActionButton = {
            if (currentRoute != "Login")
                FloatingActionButton(onClick = {
                    onEvent(DonationEvent.SetDonatedType("Krew pełna"))
                    onEvent(
                        DonationEvent.SetCreatedAt(
                            DateTime.parse("01/10/2012").toInstant().millis
                        )
                    )
                    onEvent(DonationEvent.SetAmount(450))
                    onEvent(DonationEvent.SaveDonation)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        NavHost(navController = navController, startDestination = Routes.LOGIN) {
            composable(route = Routes.LOGIN) {
                SignInScreen(navController)
            }
            composable(route = Routes.JOURNAL) {
                DonationJournal(name, state, onEvent)
            }
            composable(route = Routes.SELF) {
                MainPage(name)
            }

        }
    }
}