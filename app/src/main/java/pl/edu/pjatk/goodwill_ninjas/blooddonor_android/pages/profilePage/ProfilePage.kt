package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.profilePage

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user.UserResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.UserCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.carousel.Carousel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.userFeat.UserFeatViewModel

@Composable
fun ProfilePage (
    navController: NavController,
    context: Context,
    db: AppDatabase
) {
    val loginViewModel = LoginViewModel(context)
    
    var token = loginViewModel.getToken()
    var userViewModal: UserViewModel
    var userFeatViewModel: UserFeatViewModel
    var userId = 0
    var user: UserResponse

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
                userId = userViewModal.getUserId()
                userFeatViewModel = UserFeatViewModel(context, token, db.userFeatDao())
                userFeatViewModel.getUserFeats(token)
                runBlocking {
                    userViewModal.getUser(userId, token)
                    user = userViewModal.state.value
                }
                Button(onClick = {
                    loginViewModel.logout()
                    token = loginViewModel.getToken()
                    navController.navigate(Routes.LOGIN)
                }) {
                    Text(text = "Wyloguj się")
                }
                Spacer(modifier = Modifier.height(50.dp))
                UserCard(userData = user, context = context)
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = "Odznaki", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                if (userFeatViewModel.getFeats().isNotEmpty()) {
                    Carousel(items = userFeatViewModel.getFeats())
                } else {
                    Text("Nie masz jeszcze żadnych odznak!")
                }
            })
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}