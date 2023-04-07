package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

<<<<<<< HEAD


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
=======
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
>>>>>>> e866ba6 (Add calendar picker)
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

<<<<<<< HEAD

import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DatePicker(

) {
    var datePickerState = rememberDatePickerState()
    Column {
        DatePicker(
            datePickerState = datePickerState,
            modifier = Modifier.padding(16.dp)
        )
        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")

        BloodQtyDonated()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodQtyDonated() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pattern = remember { Regex("^[0-9][0-9][0-9]") }
        var bloodQtyDonated by remember {
            mutableStateOf("450")
        }
        val newBloodQtyDonated = 0
        OutlinedTextField(

            value = bloodQtyDonated, onValueChange = {


                    newBloodQtyDonated ->
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

@Composable
fun DateDisplay() {

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium

        )

    }
}
=======
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(

) {
    val datePickerState = rememberDatePickerState()
    DatePicker(
        datePickerState = datePickerState,
        modifier = Modifier.padding(16.dp)
    )
    Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
}


>>>>>>> e866ba6 (Add calendar picker)


@Preview()
@Composable
fun DatePickerPreview() {

    Surface {

        DatePicker()

<<<<<<< HEAD

    }

}

=======
    }

}
>>>>>>> e866ba6 (Add calendar picker)
