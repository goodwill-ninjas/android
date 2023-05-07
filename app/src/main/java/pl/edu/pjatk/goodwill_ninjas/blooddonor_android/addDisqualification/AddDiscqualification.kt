package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDisqualification
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker.DatePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen

@Composable
fun AddDisqualification(navController: NavController, onEvent: (DonationEvent) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
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

                        Column {
                            Row {
                                Text(
                                    text = "Wybierz datę początku dyskwalifikacji",
                                    fontSize = 20.sp,
                                )
                            }
                            Row { DatePicker(onEvent) }
                        }
                        Column {
                            Row {
                                Text(
                                    text = "Wybierz datę końca dyskwalifikacji",
                                    fontSize = 20.sp,
                                )
                            }
                            Row { DatePicker(onEvent) }
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