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
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.BloodQtyDonated
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
dateConverter: DateConverter
) {

    var datePickerState = rememberDatePickerState()

    Column {
        androidx.compose.material3.DatePicker(
            datePickerState = datePickerState,
            modifier = Modifier.padding(16.dp)
        )
        Text("Data wybrana: ${datePickerState.selectedDateMillis ?: "no selection"}")


    }
}


@Preview
@Composable
fun prev(){

    DatePicker()
}