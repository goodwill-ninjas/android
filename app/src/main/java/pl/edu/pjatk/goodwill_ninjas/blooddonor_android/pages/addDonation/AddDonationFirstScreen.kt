package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.addDonation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.BloodQtyInput
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.DatePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.dropDownMenuBloodCentre
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.storeViewModel.ExchangeViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.rememberImeState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenter.BloodCenterViewModel
import java.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(navController: NavController, onEvent: (DonationEvent) -> Unit, onEventDisqualification: (DisqualificationEvent) -> Unit,
                  exchangeViewModel: ExchangeViewModel, context: Context, db: AppDatabase) {
    var scrollState = rememberScrollState()
    val imeState = rememberImeState()
    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    Scaffold {
        val image = painterResource(id = R.drawable.droplet)
        Column(
            modifier = Modifier
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
                .verticalScroll(scrollState)
                .fillMaxWidth()
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
                        Row {
                            Text(
                                text = "Dodaj donację",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row {
                            GetDate()
                        }
                        Row {
                            DisplayDonationOptions(onEvent, exchangeViewModel)
                        }
                        Row {
                            DatePicker(onEvent, onEventDisqualification, exchangeViewModel)
                        }
                        Row {
                            dropDownMenuBloodCentre(onEvent, exchangeViewModel, context, db)
                        }
                        Row {
                            OutlinedButton(
                                onClick = {
                                    navController.navigate(Screen.AdvancedDonationParams.route)
                                },
                                modifier = Modifier.width(200.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.Red
                                ),
                                shape = RoundedCornerShape(5),
                            )
                            {
                                Text(
                                    text = "ZAAWANSOWANE",
                                    fontSize = 16.sp,
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
@Composable
fun DisplayDonationOptions(onEvent: (DonationEvent) -> Unit, exchangeViewModel: ExchangeViewModel) {
    BlooddonorandroidTheme {
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalArrangement = Arrangement.Top
        ) {
            dropDownMenuDonationType(onEvent, exchangeViewModel)
        }
    }
}
@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun dropDownMenuDonationType(onEvent: (DonationEvent) -> Unit, exchangeViewModel: ExchangeViewModel) {
    val options = listOf(
        "Krew pełna",
        "Osocze",
        "Płytki krwi",
        "Krwinki czerwone",
        "Krwinki białe"
    )
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf(options[0])
    }
    var mContext = LocalContext.current
    var childOptions by remember {
        mutableStateOf("")
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded }) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            readOnly = true,
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            options.forEach { eachoption ->
                DropdownMenuItem(onClick = {
                    selectedOption = eachoption
                    isExpanded = false
                    Toast.makeText(
                        mContext,
                        "" + selectedOption,
                        Toast.LENGTH_LONG
                    ).show()
                    childOptions = ""
                    if (selectedOption.equals("Krew pełna")) {
                        childOptions += "450"
                    } else if (selectedOption.equals("Płytki krwi")) {
                        childOptions += "250"
                    } else if (selectedOption.equals("Krwinki czerwone")) {
                        childOptions += "600"
                    } else if (selectedOption.equals("Krwinki białe")) {
                        childOptions += "200"
                    } else if (selectedOption.equals("Osocze")) {
                        childOptions += "650"
                    }
                }) {
                    Text(text = eachoption)
                }
            }
        }
        onEvent(DonationEvent.SetDonatedType(selectedOption))
        val run = runBlocking {
            exchangeViewModel.saveDonationType(selectedOption)
        }
    }
    Column {
        Row {
            BloodQtyInput(onEvent, exchangeViewModel)
        }
    }
}

