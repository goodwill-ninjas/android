package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
<<<<<<< HEAD
=======
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
>>>>>>> 87b5fd060d94eac67db7a9c7a14f4ad825dd7285
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
    ) {
        Box(modifier = Modifier.padding(15.dp)) {
            Image(painter = image, contentDescription = null, Modifier.height(250.dp))
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
                    Screen.BottomSheetDialog.route
                )
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        val image = painterResource(id = R.drawable.droplet)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding()) {
                Image(painter = image, contentDescription = null, Modifier.height(250.dp))
            }
            Box(modifier = Modifier.padding()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clickable { },
                    elevation = 10.dp
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(text = stringResource(R.string.full_blood))
                        Text(
                            buildAnnotatedString {
                                append(
                                    bloodDonation.date.format(DateTimeFormatter.ISO_DATE).toString()
                                )
                            }
                        )
                        Text(
                            stringResource(
                                id = R.string.next_donation,
                                bloodDonation.calculateNextDonation()
                            )
                        )
                    }
                }
            }
>>>>>>> 87b5fd060d94eac67db7a9c7a14f4ad825dd7285
        }
        BloodCard(bloodType = stringResource(R.string.full_blood), isNextDonationCard = true, amount = 0, donationDate = LocalDateTime.of(2023, 2, 23, 0, 0))
    }
}

