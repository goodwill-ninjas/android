package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.profilePage

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeat
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.healthCheck.HealthCheckViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.userFeat.UserFeatViewModel

@Composable
fun ProfilePage(navController: NavController, context: Context, db: AppDatabase) {
    val loginViewModel = LoginViewModel(context)
    
    var token = loginViewModel.getToken()
    var userViewModal: UserViewModel
    var userFeatViewModel: UserFeatViewModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            (if (token.isEmpty()) {
                Text(text = "Jesteś wylogowany, aby korzystać z aplikacji zaloguj się.")
                Button(onClick = { navController.navigate(Routes.LOGIN) }) {
                    Text(text = "Zaloguj się")
                }
                Button(onClick = { navController.navigate(Routes.REGISTER)}) {
                    Text(text = "Zarejestruj się")
                }
            } else {
                userViewModal = UserViewModel(context, token)
                userFeatViewModel = UserFeatViewModel(context, token, db.userFeatDao())

                Button(onClick = {
                    loginViewModel.logout()
                    token = loginViewModel.getToken()
                }) {
                    Text(text = "Wyloguj się")
                }
                Button(onClick = { userFeatViewModel.getUserFeats(token) }) {
                    Text(text = "Show Feats")
                }
            })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Token: $token")
        }
    }
}