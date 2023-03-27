package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.annotation.SuppressLint
import android.util.Log
import android.view.Surface
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import java.text.DateFormat
import java.time.LocalDate
import java.util.Calendar


@Composable

fun WelcomeScreen() {
    val paddingModifier = Modifier.padding(10.dp)
    Column(
        modifier = Modifier.width(300.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(

        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),

                ) {
                Text(
                    text = "Krew pełna", fontSize = 20.sp, fontWeight = FontWeight.Bold,


                    )
                Column {
                    GetDate()
                }

            }
        }

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = paddingModifier
        ) {

            Text(text = "Dodaj donację", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            DateButton()
//            BloodQtyInput()


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
        alert(msg = "Hello, this is an alert dialog!",
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false })
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = {
//                showDialog.value = true
            }
        ) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun alert(
    msg: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(msg)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Dismiss")
                }
            },
            dismissButton = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BloodQtyInput() {

    var value by remember {
        mutableStateOf("false")
    }

    val context = LocalContext.current.applicationContext

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
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
                    Toast.makeText(context, "On Search Click: value = $value", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        )
    }
}


@Preview()
@Composable
fun WelcomeScreenPreview() {
    Surface {
        WelcomeScreen()
    }
}