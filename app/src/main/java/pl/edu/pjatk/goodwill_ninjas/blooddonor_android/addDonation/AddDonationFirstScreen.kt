package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker.DatePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker.DatePickerService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.rememberImeState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationState
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(navController: NavController, onEvent: (DonationEvent) -> Unit) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var datePickerService = DatePickerService()
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
                .verticalScroll(scrollState)
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
                            DisplayDonationOptions(onEvent)
                        }
                        Row {
                            DatePicker(onEvent)
                        }
                        Row {
                            Button(
                                onClick = {
                                    onEvent(DonationEvent.SetAmount(10))
                                    onEvent(DonationEvent.SetDonatedType("Krew pelna"))
                                    onEvent(DonationEvent.SaveDonation)
                                }) {

                            }
                        }
                        DatePicker(onEvent)
                    }
                    Row {
                        dropDownMenuRck(onEvent)
                    }
                    Row {
                        OutlinedButton(
                            onClick = {
                                navController.navigate(Screen.AdvancedDonationParams.route)
                            },
                            modifier = Modifier.width(200.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.Red
                            ),
                            shape = RoundedCornerShape(5),
                        )
                        {
                            Text(
                                text = "ZAAWANSOWANE",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }


            }
        }
    }
}

@Composable
fun dropDownMenuRck(onEvent: (DonationEvent) -> Unit) {
    BlooddonorandroidTheme {
        val options = listOf(
            "RCKiK Gdańsk",
            "RCKiK Warszawa",
            "RCKiK Poznań",
            "RCKiK Kraków"
        )
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var selectedOption by remember {
            mutableStateOf("RCKiK Gdańsk")

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box {
                TextButton(onClick = { isExpanded = true }) {
                    Row {
                        Text(text = "$selectedOption", fontSize = 20.sp)
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                }
                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    options.forEach {
                        DropdownMenuItem(onClick = {
                            isExpanded = false
                            selectedOption = it
                        }) {
                            Text(text = it, fontSize = 20.sp)

                        }
                    }

                }
                onEvent(DonationEvent.SetBloodCenter(selectedOption))
            }
        }
    }
}

@Composable
fun DisplayDonationOptions(onEvent: (DonationEvent) -> Unit) {
    BlooddonorandroidTheme {
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalArrangement = Arrangement.Top
        ) {
            dropDownMenuDonationType(onEvent)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun dropDownMenuDonationType(onEvent: (DonationEvent) -> Unit) {
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

//                    OutlinedTextField(
//                        value = eachoption,
//                        onValueChange = {},
//                        readOnly = false,
//                        textStyle = TextStyle.Default.copy(fontSize = 14.sp)
//                    )
                }
            }
        }
        onEvent(DonationEvent.SetDonatedType(selectedOption))
    }
    Column {
        Row {
            BloodQtyInput(onEvent)
        }
    }
}