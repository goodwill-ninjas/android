package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MytopBar() },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar() },
        floatingActionButton = {
            androidx.compose.material.FloatingActionButton(onClick = {
                navController.navigate(
                    Screen.BottomSheetDialog.route
                )
            }) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
//        val image = painterResource(id = R.drawable.droplet)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding()) {
//                Image(painter = image, contentDescription = null, Modifier.height(250.dp))
            }
            Box(modifier = Modifier.padding()) {
                androidx.compose.material.Card(
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
                                text = "Krew pełna", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                            )
                        }
                        Row() {
                            GetDate()
                        }
                        Row {
                            Text(
                                text = "Dodaj donację",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row {
                            CustomDateDialog()
                        }
                        Row {
                            BloodQtyInput()
                        }
                        Row {
                            dropDownMenuRck()
                        }
                        Row {
                            Button(onClick = {
                                navController.navigate(Screen.AdvancedDonationParams.route)
                            }) {
                                Text(text = "Zaawansowane")
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun DateButtonInAddDonation() {
    var bloodQty by remember {
        mutableStateOf("")
    }
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        AlertInAddDonation(msg = "Tu chciałem pokazać selektor dat",
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false })
    }
    Button(
        modifier = Modifier.padding(vertical = 10.dp),
        onClick = {
            showDialog.value = true

        }
    ) {
        Text(text = "Wybierz datę donacji")
    }
}
@Composable
fun AlertInAddDonation(
    msg: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        DatePicker()
    }
}
@Composable
fun dropDownMenuRck() {
    var bloodQty by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("RCKiK Gdańsk", "RCKiK Waraszawa", "RCKiK Poznań", "RCKiK Kraków")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(Modifier.padding(20.dp)) {
        androidx.compose.material.OutlinedTextField(
            value = bloodQty,
            onValueChange = { newText -> bloodQty = newText },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { androidx.compose.material.Text("Wybierz RCKiK") },
            trailingIcon = {
                androidx.compose.material.Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        androidx.compose.material.DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                androidx.compose.material.DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    androidx.compose.material.Text(text = label)
                }
            }
        }
    }
}


