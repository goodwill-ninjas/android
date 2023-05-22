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
import kotlinx.coroutines.flow.first
import org.joda.time.DateTime
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal.DonationJournal
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.loginPage.SignInScreen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.MainPage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.profilePage.ProfilePage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.JWTUtils
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel

object Routes {
    const val SELF = "Main"
    const val JOURNAL = "Journal"
    const val LOGIN = "Login"
    const val PROFILE = "Profile"
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
    val token = LoginViewModel(context).getToken()
    val userViewModel = UserViewModel(context, JWTUtils().decoded(token))
    userViewModel.setUserId()
    userViewModel.setUserName()
    val userId = userViewModel.getUserId()
    val userName = userViewModel.getUserName()


    Scaffold(
        scaffoldState = scaffoldState,

        topBar = { MytopBar(userName) },
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
                            DateTime.parse("2012-01-10").toInstant().millis
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
        NavHost(navController = navController, startDestination = Routes.SELF) {
            composable(route = Routes.LOGIN) {
                SignInScreen(navController, context)
            }
            composable(route = Routes.JOURNAL) {
                DonationJournal(userName, state, onEvent)
            }
            composable(route = Routes.SELF) {
                MainPage(userName, navController, context)
            }
            composable(route = Routes.PROFILE) {
                ProfilePage(navController, context)
            }

        }
    }
}