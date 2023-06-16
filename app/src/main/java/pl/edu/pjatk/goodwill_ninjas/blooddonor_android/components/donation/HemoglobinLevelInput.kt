package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent


@Composable
fun HemoglobinLevelInput(onEvent: (DonationEvent) -> Unit, onDisqualificationEvent: (DisqualificationEvent)->Unit) {
    var value by remember {
        mutableStateOf("0")
    }
    val context = LocalContext.current.applicationContext
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { newText ->
                value = newText.ifEmpty { 0.toString() }
            },
            label = { Text(text = "Hemoglobina") },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            placeholder = { Text(text = "0") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    Toast.makeText(
                        context,
                        "On Search Click: value = $value",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            )
        )
        onEvent(
            if (value == "0") {
                DonationEvent.SetHemoglobin(null)
            } else {
                DonationEvent.SetHemoglobin(value.toDouble())
            })
        onDisqualificationEvent(
            if (value == "0") {
                DisqualificationEvent.SetHemoglobin(null)
            } else {
                DisqualificationEvent.SetHemoglobin(value.toDouble())
            }
        )
    }
}