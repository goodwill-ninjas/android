package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.text.DateFormat
import java.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen() {
    val navController = rememberNavController()
    Column(
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
            Text(text = "Dodaj donację", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Row {
            DateButton()
        }
        Row {
            BloodQtyInput()
        }
        Row{
            dropDownMenuRck()
        }
        Row{
            Button(onClick = {
                navController.navigate(Screen.Advanced.route)
            }) {
                Text(text = "Zaawansowane")
            }
        }
    }
}


@Composable
fun GetDate() {
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)
    Text(text = "$dateFormat")
}

//@SuppressLint("UnrememberedMutableState")
@Composable
fun DateButton() {
    var bloodQty by remember {
        mutableStateOf("")
    }
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        alert(msg = "Tu chciałem pokazać selektor dat",
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
fun alert(
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
private fun BloodQtyInput() {
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
            label = { Text(text = "Ilość") },
            placeholder = { Text(text = "Ilość") },
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


