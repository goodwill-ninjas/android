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
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.util.getDefaultDateInMillis
import java.text.SimpleDateFormat

import java.time.LocalDate
import java.time.format.DateTimeFormatter
>>>>>>> b7cc35df1b661d255575aaaced8a6d6cf9d3f2c7
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(

<<<<<<< HEAD
) {
    val datePickerState = rememberDatePickerState()
    DatePicker(
        datePickerState = datePickerState,
        modifier = Modifier.padding(16.dp)
    )
    Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
}



=======

) {
    val dateSelected = remember { mutableStateOf("") }
    val simpleDateFormatPattern = "EEE, MMM d"
    val dateFormat = SimpleDateFormat(simpleDateFormatPattern, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")


    val calendarState = rememberSheetState()

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MMM dd yyyy").format(LocalDate.now())
        }
    }
    val onSelectDate = ""
    CalendarDialog(state = calendarState,
        config = CalendarConfig(

        ),
        selection = CalendarSelection.Date { _ ->
            Log.d("selected date", "$dateSelected")

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
            Log.d("selected date", "$formattedDate")
        }

    }

}

@Composable
fun SimpleCard() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(modifier = paddingModifier) {
        Text(
            text = "Simple Card with elevation",
            modifier = paddingModifier
        )
    }
    Column() {

    }
}
>>>>>>> b7cc35df1b661d255575aaaced8a6d6cf9d3f2c7

@Preview()
@Composable
fun DatePickerPreview() {

    Surface {
<<<<<<< HEAD

        DatePicker()

=======
        DatePicker()
        SimpleCard()
>>>>>>> b7cc35df1b661d255575aaaced8a6d6cf9d3f2c7
    }

}