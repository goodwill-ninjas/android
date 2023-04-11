package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.text.DateFormat
import java.util.*

@Composable
fun GetDate() {
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)
    Text(text = "$dateFormat")
}