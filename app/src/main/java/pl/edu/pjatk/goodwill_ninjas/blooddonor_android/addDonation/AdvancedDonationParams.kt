package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FabPosition
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen

import java.util.*

@Composable
fun AdvancedDonationParams(navController: NavController) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MytopBar(name = "Wojciech") },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar(navController) },
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
                        Row() {
                            Text(text = "Krew pełna")
                        }
                        Row {
                            BloodPressureInput()
                        }
                        Row() {
                            HemoglobineLevelInput()
                        }
                        Row() {
                            ExaminationResult()
                        }
                        Row {
                            DonationHandSelector()
                        }
                        Row() {
                            BloodQtyInput()
                        }
                    }

                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExaminationResult() {
    var value by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        TextField(
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            label = { androidx.compose.material3.Text(text = "Podaj wynik badania") },
            placeholder = { Text(text = "podaj wynik badania") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    Toast.makeText(
                        context,
                        "On Search Click: value = $value",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            )
        )
    }
}

@Composable
fun DonationHandSelector() {
    var bloodQty by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Prawa", "Lewa")
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
                    textfieldSize = coordinates.size.toSize()
                },
            label = { androidx.compose.material.Text("Wybierz rękę") },
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
