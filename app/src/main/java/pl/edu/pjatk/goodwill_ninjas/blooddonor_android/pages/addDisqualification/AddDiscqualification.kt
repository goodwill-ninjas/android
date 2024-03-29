package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.addDisqualification

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.disqualification.DaysInput
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.DatePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.rememberImeState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.storeViewModel.ExchangeViewModel

@Composable
fun AddDisqualification(
    navController: NavController, onEvent: (DonationEvent) -> Unit,
    onEventDisqualification: (DisqualificationEvent) -> Unit, exchangeViewModel: ExchangeViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues = it)
        ) {
            Box(modifier = Modifier.padding(10.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
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
                            Row { DatePicker(onEvent, onEventDisqualification, exchangeViewModel) }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column {
                            Row {
                                Text(
                                    text = "Dni dyskwalifikacji",
                                    fontSize = 20.sp,
                                )
                            }
                            Row {
                                DaysInput(onEventDisqualification, exchangeViewModel)
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Row {
                                OutlinedButton(
                                    onClick = {
                                        navController.navigate(Screen.AddDisqualificationAdvanced.route)
                                    },
                                    modifier = Modifier.width(200.dp),
                                    colors = ButtonDefaults.buttonColors(
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
}