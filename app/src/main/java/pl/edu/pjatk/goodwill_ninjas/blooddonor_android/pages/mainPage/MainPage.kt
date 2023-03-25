package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodCard
import java.time.LocalDateTime


@Composable
<<<<<<< HEAD
fun MainPage(name: String) {
    val image = painterResource(id = R.drawable.droplet)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
=======
fun MainPage(name: String, bloodDonation: NextDonation, navController: NavController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = { MytopBar() },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(

//                    Screen.DatePicker.route
                    Screen.BottomSheetDialog.route
                )
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },

        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
>>>>>>> 26d5110 (Add date utils)
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
    }
}
