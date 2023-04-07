package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    Scaffold(
        scaffoldState = scaffoldState,
//        topBar = { MytopBar(name = "Wojciech") },
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = { MyBottomBar(navController) },
//        floatingActionButton = {
//            androidx.compose.material.FloatingActionButton(onClick = {
//                navController.navigate(
//                    Screen.BottomSheetDialog.route
//                )
//            }) {
//                androidx.compose.material.Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add"
//                )
//            }
//        },
//        isFloatingActionButtonDocked = true,
//        floatingActionButtonPosition = FabPosition.Center
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
                            HemoglobinLevelInput()
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
            label = { Text(text = "Podaj wynik badania") },
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
    val options = listOf("Lewa", "Prawa")
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf("Lewa")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Box() {
            TextButton(onClick = { isExpanded = true }) {
                Row {
                    Text(text = "$selectedOption")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
            }
            DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                options.forEach {
                    DropdownMenuItem(onClick = {
                        isExpanded = false
                        selectedOption = it
                    }) {
                        Text(text = it)

                    }
                }

            }
        }
    }

}