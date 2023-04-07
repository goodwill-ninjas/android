package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDisqualification

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.CustomDateDialog
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen

@Composable
fun AddDisqualification(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MytopBar(name = "Wojciech") },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar(navController) },
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

                        Column() {
                            Row() { Text(text = "Wybierz datę początku dyskwalifikacji") }

                            Row() { CustomDateDialog() }
                        }
                        Column() {
                            Row() { Text(text = "Wybierz datę końca dyskwalifikacji") }
                            Row() { CustomDateDialog() }
                        }

                        Row {
                            OutlinedButton(
                                onClick = {
                                    navController.navigate(Screen.AddDisqualificationAdvanced.route)
                                },
                                modifier = Modifier.width(200.dp),
                                colors = ButtonDefaults.buttonColors(
//                                    containerColor = Color.White,
                                    contentColor = Color.Red
                                ),
                                shape = RoundedCornerShape(5),
                            )
                            {
                                Text(
                                    text = "Zaawansowane",
                                    fontWeight = FontWeight.Bold, color = Color.White
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}