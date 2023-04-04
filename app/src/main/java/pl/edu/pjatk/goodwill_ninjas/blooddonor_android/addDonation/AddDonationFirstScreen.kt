package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.Out
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.util.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon as ExposedDropdownMenuDefaultsTrailingIcon

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
                            CustomDateDialog()
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
                                    containerColor = Color.White,
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenuRck() {
    val options = listOf("RCKiK Gdańsk", "RCKiK Warszawa", "RCKiK Poznań", "RCKiK Kraków")
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf(options[0])
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = false,
            label = Text("label"),
//            trailinIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            options.forEach { selectedText ->
                DropdownMenuItem(
                    text = { Text(selectedText) },
                    onClick = {
                        selectedOption = selectedText
                        isExpanded = false
                    },
                )
            }
        }
    }
}
