package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

import android.util.Log
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodCard
import java.time.LocalDateTime

@Composable
fun MainPage(name: String) {
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
        Log.i("mainpage", "this is MainPage")
    }
}

@Composable
@Preview
fun see(){
    MainPage(name = "My main Page")
}