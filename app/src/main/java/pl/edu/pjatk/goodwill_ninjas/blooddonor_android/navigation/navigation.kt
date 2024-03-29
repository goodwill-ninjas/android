package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation

import BottomModal
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.addDisqualification.AddDisqualification
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.addDisqualification.AddDisqualificationAdvanced
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.addDonation.AdvancedDonationParams
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BottomSheetDialog
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.addDonation.WelcomeScreen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.bloodCentersPage.BloodCentersPage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal.DonationJournal
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.loginPage.SignInScreen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage.MainPage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.profilePage.ProfilePage
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.registerPage.SignUpScreen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.discqualification.DisqualificationState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.storeViewModel.ExchangeViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel

object Routes {
    const val SELF = "Main"
    const val JOURNAL = "Journal"
    const val ADD_DONATION = "Add_donation"
    const val BOTTOM_SHEET_DIALOG = "Bottom_dialog"
    const val ADD_DISQUALIFICATION = "Add_disqualification"
    const val ADD_DISCQUALIFICATION_ADVANCED = "Add_disqualification_advanced"
    const val BottomModal = "BottomModal"
    const val ADVANCED = "Add_donation_advanced"
    const val LOGIN = "Login"
    const val PROFILE = "Profile"
    const val REGISTER = "Register"
    const val BLOOD_CENTERS = "Blood_Centers"
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation(
    state: DonationState,
    disqualificationState: DisqualificationState,
    onEvent: (DonationEvent) -> Unit,
    onEventDisqualification: (DisqualificationEvent) -> Unit,
    exchangeViewModel: ExchangeViewModel,
    db: AppDatabase
) {
    val context = LocalContext.current
    val token = LoginViewModel(context).getToken()
    val userViewModel: UserViewModel
    var userName = ""
    var userId = 0
    if (token.isNotEmpty()) {
        userViewModel = UserViewModel(context, token)
        userViewModel.setUserId()
        userViewModel.setUserName()
        userId = userViewModel.getUserId()
        userName = userViewModel.getUserName()
    }
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MytopBar(userName) },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (currentRoute != "BottomModal" && currentRoute != Routes.LOGIN && currentRoute != Routes.REGISTER) {
                FloatingActionButton(onClick = {
                    navController.navigate(Screen.BottomModal.route)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
                if (currentRoute.equals("Add_donation")) {
                    FloatingActionButton(onClick = {
                        onEvent(DonationEvent.SetAmount(exchangeViewModel.getAmount()))
                        onEvent(DonationEvent.SetCreatedAt(exchangeViewModel.getCreatedAt()))
                        onEvent(DonationEvent.SetDonatedType(exchangeViewModel.getDonationType()))
                        onEvent(DonationEvent.SetBloodCenter(exchangeViewModel.getBloodCentre()))
                        onEvent(DonationEvent.SaveDonation)
                        navController.navigate(Routes.SELF)
                    }, backgroundColor = Color.Green) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
                if (currentRoute.equals("Add_donation_advanced")) {
                    FloatingActionButton(onClick = {
                        onEvent(DonationEvent.SetAmount(exchangeViewModel.getAmount()))
                        onEvent(DonationEvent.SetCreatedAt(exchangeViewModel.getCreatedAt()))
                        onEvent(DonationEvent.SetDonatedType(exchangeViewModel.getDonationType()))
                        onEvent(DonationEvent.SetBloodCenter(exchangeViewModel.getBloodCentre()))
                        onEvent(DonationEvent.SaveDonation)
                        navController.navigate(Routes.SELF)
                    }, backgroundColor = Color.Green) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
                if (currentRoute.equals("Add_disqualification")) {
                    FloatingActionButton(onClick = {
                        onEventDisqualification(DisqualificationEvent.SetDateStart(exchangeViewModel.getDisqualificationDateStart()))
                        onEventDisqualification(
                            DisqualificationEvent.SetDays(
                                exchangeViewModel.getDisqualificationDays()
                            )
                        )
                        onEventDisqualification(DisqualificationEvent.SaveDisqualification)
                        navController.navigate(Routes.SELF)
                    }, backgroundColor = Color.Green) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
                if (currentRoute.equals("Add_disqualification_advanced")) {
                    FloatingActionButton(onClick = {
                        onEventDisqualification(DisqualificationEvent.SetDateStart(exchangeViewModel.getDisqualificationDateStart()))
                        onEventDisqualification(
                            DisqualificationEvent.SetDays(
                                exchangeViewModel.getDisqualificationDays()
                            )
                        )
                        onEventDisqualification(DisqualificationEvent.SaveDisqualification)
                        navController.navigate(Routes.SELF)
                    }, backgroundColor = Color.Green) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if (currentRoute != "BottomModal" && currentRoute != Routes.LOGIN && currentRoute != Routes.REGISTER) {
                MyBottomBar(navController)
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Routes.SELF) {
            composable(route = Routes.LOGIN) {
                SignInScreen(navController, context)
            }
            composable(route = Routes.JOURNAL) {
                DonationJournal(userName, state, disqualificationState, onEvent, db, context, userId, token, navController)
            }
            composable(route = Routes.SELF) {
                MainPage(userName, navController, context, db)
            }
            composable(route = Routes.PROFILE) {
                ProfilePage(navController, context, db)
            }
            composable(route = Routes.REGISTER) {
                SignUpScreen(navController, context)
            }
            composable(route = Routes.BLOOD_CENTERS) {
                BloodCentersPage(navController, context, db)
            }
            composable(route = Routes.ADD_DONATION) {
                WelcomeScreen(navController, onEvent, onEventDisqualification, exchangeViewModel, context, db)

            }
            composable(route = Routes.BOTTOM_SHEET_DIALOG) {
                BottomSheetDialog(navController)

            }
            composable(route = Routes.ADVANCED) {
                AdvancedDonationParams(onEvent, state, exchangeViewModel, onEventDisqualification)
            }
            composable(route = Routes.ADD_DISQUALIFICATION) {
                AddDisqualification(
                    navController,
                    onEvent,
                    onEventDisqualification,
                    exchangeViewModel
                )
            }
            composable(route = Routes.ADD_DISCQUALIFICATION_ADVANCED) {
                AddDisqualificationAdvanced(onEvent, onEventDisqualification)

            }
            composable(route = Routes.BottomModal) {
                BottomModal(navController)
            }
        }
    }
    Spacer(modifier = Modifier.width(40.dp))
}