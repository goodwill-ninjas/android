package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
import android.graphics.Paint
import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import java.text.DateFormat
import java.util.Calendar
import kotlin.contracts.contract


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen() {

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

        Row {
            dropDownMenuRck()

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
        modifier = Modifier.fillMaxSize(),
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
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//
//    var selectedItem by remember {
//        mutableStateOf("RCKiK")
//    }
//    val rckkList = listOf ( "RCKik Gdańsk", "RCKiK Warszawa" )
//    Box {
//            TextButton(onClick = {expanded = true}) {
//                Row{
//                    Text(text = "$selectedItem")
//                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
//                }
//            }
//        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
//            rckkList.forEach {
//                DropdownMenuItem(onClick = {expanded = false
//                selectedItem = it
//                }) {
//                    Text(text = it)
//
//                }
//            }
//
//        }
//    }
}

@Preview()
@Composable
fun FirstScreen() {

    Surface() {

        WelcomeScreen()
    }

}

