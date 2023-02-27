package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

<<<<<<< HEAD
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
=======
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
>>>>>>> dd82d0b (Add calendar picker)
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
<<<<<<< HEAD
fun DatePicker(

) {
    val datePickerState = rememberDatePickerState()
    DatePicker(
        datePickerState = datePickerState,
        modifier = Modifier.padding(16.dp)
    )
    Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
}




=======
fun DatePicker() {
    val calendarState = rememberSheetState()

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MMM dd yyyy").format(LocalDate.now())
        }
    }
    CalendarDialog(state = calendarState,
        config = CalendarConfig(
            style = CalendarStyle.WEEK
        ),
        selection = CalendarSelection.Date { val onSelectDate: (date: LocalDate) -> Unit

        })
    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(onClick = { calendarState.show() }) {
            Text(text = "Wybierz datę: $formattedDate")

        }


    }

}


>>>>>>> dd82d0b (Add calendar picker)
@Preview()
@Composable
fun DatePickerPreview() {

    Surface {
<<<<<<< HEAD

        DatePicker()

=======
        DatePicker(

        )
>>>>>>> dd82d0b (Add calendar picker)
    }

}