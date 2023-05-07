package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.joda.time.Instant
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationViewModel
import java.text.DateFormat
import java.util.Calendar

@Composable
fun DatePicker(
    onEvent: (DonationEvent) -> Unit
) {

    BlooddonorandroidTheme {
        var datePickerService = DatePickerService()
        var dateReader = DateReader(datePickerService)
        var firstDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd")
        var date by remember { mutableStateOf(LocalDate.parse(LocalDate.now().toString(), firstDateFormat).toString()) }
        var dateformatted = LocalDate.parse(date , firstDateFormat)
        val day: Int
        val month: Int
        val year: Int
        val mCalendar = Calendar.getInstance()
        day = mCalendar.get(Calendar.DAY_OF_MONTH)
        month = mCalendar.get(Calendar.MONTH)
        year = mCalendar.get(Calendar.YEAR)
        val mDatePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, day: Int, month: Int, year: Int -> date = "$day-${month + 1}-$year" },
            year,
            month,
            day
        )
        mDatePickerDialog.datePicker.minDate = mCalendar.timeInMillis
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.align(Alignment.CenterStart)) {
                OutlinedTextField(value = (date),
                    onValueChange = {
                        date = it
                    },
                    readOnly = true,
                    label = { Text(text = "Data") }
                )
                onEvent(DonationEvent.SetCreatedAt(Instant.parse(LocalDate.parse(date , firstDateFormat).toString()).millis))
                Log.d("dateRegularFormat", dateformatted.toString())
                Log.d("dateMillis2", Instant.parse(dateformatted.toString()).millis.toString())
                var dateformattedTomillis = Instant.parse(dateformatted.toString()).millis
                datePickerService.date = dateformattedTomillis
                dateReader.update()
                Icon(
                    imageVector = Icons.Filled.DateRange, contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp)
                        .clickable {
                            mDatePickerDialog.show()
                        }
                )
            }
        }
    }

}
