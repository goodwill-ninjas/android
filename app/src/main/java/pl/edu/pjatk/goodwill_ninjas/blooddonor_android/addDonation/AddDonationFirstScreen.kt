package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(navController: NavController) {
//    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
   Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = { MytopBar(name = "Wojciech") },
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = { MyBottomBar(navController) },
//        floatingActionButton = {
//           FloatingActionButton(onClick = {
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
        val image = painterResource(id = R.drawable.droplet)
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
                            DatePicker()
                        }
                        Row {
                            BloodQtyInput()
                        }
                        Row {
                            dropDownMenuRck()
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
                                Text(text = "ZAAWANSOWANE", fontWeight = FontWeight.Bold)
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
    val options = listOf("RCKiK Gdańsk", "RCKiK Warszawa", "RCKiK Poznań", "RCKiK Kraków")
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf("RCKiK Gdańsk")
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

