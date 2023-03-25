package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

import android.widget.GridView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.HourglassFull
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.ScaffoldAndFAB
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.time.format.DateTimeFormatter

@Composable
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
        }
    }
}

