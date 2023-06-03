package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.healthCheck.HealthCheckService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login.LoginService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.JWTUtils
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.healthCheck.HealthCheckViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.userFeat.UserFeatViewModel
import java.time.LocalDateTime

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainPage(name: String, navController: NavController, context: Context) {
    val loginViewModel = LoginViewModel(context)
    val token: String = loginViewModel.getToken()
    var userViewModel: UserViewModel
    var userId = 0
    val userFeatViewModel: UserFeatViewModel

    val image = painterResource(id = R.drawable.droplet)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(15.dp)) {
            if (token.isNotEmpty() && userId != 0) {
                Image(painter = image, contentDescription = null, Modifier.height(250.dp))
            }
        }
        BloodCard(bloodType = stringResource(R.string.full_blood), isNextDonationCard = true, amount = 0, donationDate = LocalDateTime.of(2023, 2, 23, 0, 0))
        if (token.isNotEmpty()) {
            userViewModel = UserViewModel(context, token)
            userId = userViewModel.getUserId()
            Button(onClick = {
                userViewModel.getUser(userId, token)
            }) {
                Text(text = "HealthCheck")
            }
            Button(onClick = {
                Log.d("UserState", userViewModel.state.value.toString())
                Log.d("TokenBody", token)
            }) {
                Text(text = "Log userState")
            }
        }
    }
}
