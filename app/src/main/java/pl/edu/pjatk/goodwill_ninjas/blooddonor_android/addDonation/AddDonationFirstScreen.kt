package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
<<<<<<< HEAD
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
=======
>>>>>>> 78a55f5 (Add advanced and disqualification)
import androidx.compose.foundation.clickable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
<<<<<<< HEAD
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme.colors
=======
import androidx.compose.material.FabPosition
>>>>>>> 78a55f5 (Add advanced and disqualification)
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
<<<<<<< HEAD
import androidx.compose.material.icons.rounded.KeyboardArrowDown
=======
>>>>>>> 78a55f5 (Add advanced and disqualification)
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.util.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon as ExposedDropdownMenuDefaultsTrailingIcon
=======
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.util.*
>>>>>>> 78a55f5 (Add advanced and disqualification)

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
<<<<<<< HEAD
        val image = painterResource(id = R.drawable.droplet)
=======
>>>>>>> 78a55f5 (Add advanced and disqualification)
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
<<<<<<< HEAD
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
=======
                            Button(onClick = {
                                navController.navigate(Screen.AdvancedDonationParams.route)
                            }) {
                                Text(text = "Zaawansowane")
>>>>>>> 78a55f5 (Add advanced and disqualification)
                            }
                        }
                    }
                }
            }
        }
    }
}
<<<<<<< HEAD

=======
>>>>>>> 78a55f5 (Add advanced and disqualification)
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
<<<<<<< HEAD

=======
>>>>>>> 78a55f5 (Add advanced and disqualification)
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
<<<<<<< HEAD
@OptIn(ExperimentalMaterial3Api::class)
=======
>>>>>>> 78a55f5 (Add advanced and disqualification)
@Composable
fun dropDownMenuRck() {
    val options = listOf("RCKiK Gdańsk", "RCKiK Warszawa", "RCKiK Poznań", "RCKiK Kraków")
    var isExpanded by remember {
        mutableStateOf(false)
    }
<<<<<<< HEAD
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
=======
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
>>>>>>> 78a55f5 (Add advanced and disqualification)
            }
        }
    }
}