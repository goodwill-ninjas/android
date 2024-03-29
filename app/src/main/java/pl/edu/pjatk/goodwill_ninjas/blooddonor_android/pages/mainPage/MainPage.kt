package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user.UserResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.mainPageBadge.MainPageBadge
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeat
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.userFeat.UserFeatViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainPage(name: String, navController: NavController, context: Context, db: AppDatabase) {
    val loginViewModel = LoginViewModel(context)
    val token: String = loginViewModel.getToken()
    var userViewModel: UserViewModel
    var userId = 0
    var userFeatViewModel: UserFeatViewModel
    var userFeats: List<UserFeat>
    var user: UserResponse

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("Token", token)
        if (token.isNotEmpty()) {
            userViewModel = UserViewModel(context, token)
            userId = userViewModel.getUserId()
            userViewModel.getUser(userId, token)
            userFeatViewModel = UserFeatViewModel(context, token, dao = db.userFeatDao())
            userFeatViewModel.getUserFeats(token)
            userFeats = userFeatViewModel.getFeats()
            runBlocking {
                userViewModel.getUser(userId, token)
                user = userViewModel.state.value
            }
            Box(modifier = Modifier.padding(15.dp)) {
                if(userFeats.isNotEmpty() && userFeats.any{ item -> item.featName == "Zasłużony Dawca Krwi" }) {
                    userFeats.first { item -> item.featName == "Zasłużony Dawca Krwi" }.achievedRanks?.let { it -> MainPageBadge(featRanksNumber = it.count{it.isDigit()}) }
                }
                else {
                    MainPageBadge(featRanksNumber = 0)
                }
            }
            if (user.username != null) {
                val nextDonationDate: LocalDateTime =
                    if (user.can_donate_after == null) LocalDateTime.now()
                    else LocalDateTime.parse(user.can_donate_after, DateTimeFormatter.ISO_DATE_TIME)
                BloodCard(
                    bloodType = stringResource(R.string.full_blood),
                    isNextDonationCard = true,
                    amount = 0,
                    donationDate = nextDonationDate
                )
            }
        } else {
            navController.navigate(Routes.PROFILE)
        }
    }
}
