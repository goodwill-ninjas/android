package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
@Composable
fun GetDate() {
    var date = LocalDate.now()
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)
    var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    var formattedDate = date.format(formatter)
    Text(text = "Data: " + "$formattedDate")

}