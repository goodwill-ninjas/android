package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.profilePage

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.healthCheck.HealthCheckViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel

@Composable
fun ProfilePage(navController: NavController, context: Context) {
    val loginViewModel = LoginViewModel(context)
    val healthCheckViewModel = HealthCheckViewModel(context)
    
    var token = loginViewModel.getToken()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            (if (token.isEmpty()) {
                Button(onClick = { navController.navigate(Routes.LOGIN) }) {
                    Text(text = "Zaloguj się")
                }
            } else {
                Button(onClick = {
                    loginViewModel.logout()
                    token = loginViewModel.getToken()
                }) {
                    Text(text = "Wyloguj się")
                }
            })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Token: ${token}")
        }
    }
}