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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import java.time.LocalDateTime

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
    fun MainPage(name: String, navController: NavController, context: Context) {
        val loginViewModel = LoginViewModel(context)
        val token: String = loginViewModel.getToken()
        var userViewModel: UserViewModel
        var userId = 0
        val image = painterResource(id = R.drawable.droplet)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(15.dp)) {
                Image(painter = image, contentDescription = null, Modifier.height(250.dp))
            }
            BloodCard(
                bloodType = stringResource(R.string.full_blood),
                isNextDonationCard = true,
                amount = 0,
                donationDate = LocalDateTime.of(2023, 2, 23, 0, 0)
            )
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
            BloodCard(
                bloodType = stringResource(R.string.full_blood),
                isNextDonationCard = true,
                amount = 0,
                donationDate = LocalDateTime.of(2023, 2, 23, 0, 0)
            )

        }
//    BloodCard(
//        bloodType = stringResource(R.string.full_blood),
//        isNextDonationCard = true,
//        amount = 0,
//        donationDate = LocalDateTime.of(2023, 2, 23, 0, 0)
//    )
        Log.i("mainpage", "this is MainPage")
    }