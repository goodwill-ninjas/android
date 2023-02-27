package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

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




@Preview()
@Composable
fun DatePickerPreview() {

    Surface {

        DatePicker()

    }

}