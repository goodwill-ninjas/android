package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.bloodCentersPage

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Database
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenter.BloodCenterViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel

@Composable
fun BloodCentersPage (navController: NavController, context: Context, db: AppDatabase) {
    val loginViewModel = LoginViewModel(context)
    val token = loginViewModel.getToken()
    var bloodCenterViewModel: BloodCenterViewModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        if (token.isEmpty()) {
            navController.navigate(Routes.PROFILE)
        } else {
            bloodCenterViewModel = BloodCenterViewModel(context, token)
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(text = bloodCenterViewModel.getBloodCenters(token).toString())
            }
        }
    }
}