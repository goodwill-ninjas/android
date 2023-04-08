package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDisqualification

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodPressureInput
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.HemoglobineLevelInput
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MyBottomBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.MytopBar
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen

@Composable
fun AddDisqualificationAdvanced(navHostController: NavHostController) {
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
                            DiscqualificationDescription()
                        }
                    }

                }
            }
        }
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiscqualificationDescription() {
    var value by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        TextField(
            modifier = Modifier.height(45.dp),
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            label = { Text(text = "Podaj przyczynę dyskwalifikacji") },
            placeholder = { Text(text = "Podaj przyczynę dyskwalifikacji") },
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
