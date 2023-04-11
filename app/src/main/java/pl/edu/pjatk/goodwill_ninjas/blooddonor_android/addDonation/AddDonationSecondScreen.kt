package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*


//@Composable
//fun DatePicker(
//) {
//    var datePickerState = rememberDatePickerState()
//    Column {
//        DatePicker()
//        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
//
//        BloodQtyDonated()
//    }
//}

@Composable
fun BloodQtyDonated() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pattern = remember { Regex("^[0-9][0-9][0-9]") }
        var bloodQtyDonated by remember {
            mutableStateOf("450")
        }
        val newBloodQtyDonated = 0
        OutlinedTextField(
            value = bloodQtyDonated, onValueChange = { newBloodQtyDonated ->
                bloodQtyDonated = newBloodQtyDonated

            },
            label = {
                Text(text = "Ilość ml")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = { Log.d("ImeAction", "clicked") }
            )
        )
    }
}



