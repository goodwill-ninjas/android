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
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    Scaffold {
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
                            DisplayDonationOptions()
                        }
                        Row {
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
        Box {
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


@Composable
@Preview
fun DisplayDonationOptions() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), verticalArrangement = Arrangement.Top
    ) {
        dropDownMenuDonationType()
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun dropDownMenuDonationType() {
    val options = listOf("Krew pełna", "Osocze", "Płytki krwi", "Krwinki czerwone", "Krwinki białe")
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
        TextField(
            value = selectedOption,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            readOnly = true,
            textStyle = TextStyle.Default.copy(fontSize = 14.sp)
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            options.forEach { eachoption ->
                DropdownMenuItem(onClick = {
                    selectedOption = eachoption
                    isExpanded = false
                    Toast.makeText(mContext, "" + selectedOption, Toast.LENGTH_LONG).show()
                    childOptions = ""
                    if (selectedOption.equals("Krew pełna")) {
                        childOptions += "450"
                    } else if (selectedOption.equals("Płytki krwi")) {
                        childOptions += "250"
                    } else if (selectedOption.equals("Krwinki czerwone")) {
                        childOptions += "2x300"
                    } else if (selectedOption.equals("Krwinki białe")) {
                        childOptions += "200"
                        Log.d("krwinki", childOptions)
                    } else if (selectedOption.equals("Osocze")) {
                        childOptions += "650"

                    }

                }) {
                    Text(text = eachoption)

                }
            }
        }
    }
    Column {
        Row {
            TextField(
                value = childOptions,
                onValueChange = { childOptions = it },
                readOnly = false,
                textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
            )
        }
    }
}


//
//    ExposedDropdownMenuBox(
//        expanded = expandedStateChild,
//        onExpandedChange = { expandedStateChild = !expandedStateChild },
//        modifier = Modifier.padding(20.dp)
//    ) {
//        TextField(
//            value = "selectedOptionChild",
//            onValueChange = {},
//            trailingIcon = {
//                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStateChild)
//            },
//            readOnly = true,
//            textStyle = TextStyle.Default.copy(fontSize = 14.sp)
//        )
//        ExposedDropdownMenu(
//            expanded = expandedStateChild,
//            onDismissRequest = { expandedStateChild = false }) {
//            childOptions.forEach { eachoption ->
//                DropdownMenuItem(onClick = {
//                    selectedOptionChild = eachoption.toString()
//                    expandedStateChild = false
//                    Toast.makeText(mContext, "" + selectedOptionChild, Toast.LENGTH_LONG).show()
//                }) {
//                    Text(text = eachoption)
//                }
//            }
//        }
//    }
//}


@Composable
@Preview
fun seeIt() {
    WelcomeScreen(navController = NavController(LocalContext.current))
}