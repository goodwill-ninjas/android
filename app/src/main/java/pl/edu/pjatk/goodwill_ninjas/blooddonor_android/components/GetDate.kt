package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import androidx.compose.runtime.Composable
import java.text.DateFormat
import java.util.*

@Composable
fun GetDate() {
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)
    androidx.compose.material3.Text(text = "$dateFormat")
}