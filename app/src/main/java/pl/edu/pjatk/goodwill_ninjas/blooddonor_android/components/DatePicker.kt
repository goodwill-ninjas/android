package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.BloodQtyDonated
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
<<<<<<< HEAD
) {
    var datePickerState = rememberDatePickerState()
=======

) {
    var dateTime: String
    var datePickerState = rememberDatePickerState()
    var simpleDateFormat: SimpleDateFormat
    var calendar: Calendar
    calendar = Calendar.getInstance()
    simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa z")
    dateTime = simpleDateFormat.format(datePickerState.selectedDateMillis).toString()

>>>>>>> 1cb9235 (Add date utils)
    androidx.compose.material3.DatePicker(
        datePickerState = datePickerState,
        modifier = Modifier.padding(16.dp)
    )
<<<<<<< HEAD
    Text(
        text = "${
            if (datePickerState.selectedDateMillis != null)
                Date(datePickerState.selectedDateMillis!!)
            else
                "No selection"
        }"
    )
=======

    Text("Data wybrana: ${dateTime ?: "no selection"}")


>>>>>>> 1cb9235 (Add date utils)
}


@Preview
@Composable
fun prev() {

    DatePicker()
}


