package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.bloodCentersPage

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Database
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.bloodCenter.BloodCenterCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenter
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
            .padding(10.dp, bottom = 60.dp)
    ) {
        if (token.isEmpty()) {
            navController.navigate(Routes.PROFILE)
        } else {
            bloodCenterViewModel = BloodCenterViewModel(context, token, db.bloodCenterDao())
            bloodCenterViewModel.getBloodCenters(token)
            val scrollableState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .verticalScroll(scrollableState)
            ) {
                val bloodCenters = runBlocking {
                    bloodCenterViewModel.getBloodCenters()
                }
                bloodCenters.forEach {
                    BloodCenterCard(bloodCenter = it, bloodCenterViewModel, context, token)
                }
            }
        }
    }
}