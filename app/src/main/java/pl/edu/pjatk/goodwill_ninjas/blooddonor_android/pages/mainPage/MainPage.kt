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
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.healthCheck.HealthCheckViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import java.time.LocalDateTime


//fun main() = runBlocking {
//    val service = LoginService()
//
//    coroutineScope {
//        launch(Dispatchers.IO) {
//            println("[${Thread.currentThread().name}] ONE")
//            service.successfulLoginResponse()
//
//        }
//        println("[${Thread.currentThread().name}] Done!")
//    }
//}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainPage(name: String, navController: NavController, context: Context) {
    val loginViewModel = LoginViewModel(context)
    val healthCheckViewModel = HealthCheckViewModel(context)

//    healthCheckViewModel.healthCheck()

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
        BloodCard(bloodType = stringResource(R.string.full_blood), isNextDonationCard = true, amount = 0, donationDate = LocalDateTime.of(2023, 2, 23, 0, 0))
        Button(onClick = { navController.navigate(Routes.LOGIN) }) {
            Text(text = "Login")
        }
        Button(onClick = { Log.d("token", loginViewModel.getToken()) }) {
            Text(text = "Show Token")
        }
    }
}
