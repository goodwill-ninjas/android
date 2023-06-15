package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.storeViewModel.ExchangeViewModel

@Composable
fun BloodQtyInput(
    onEvent: (DonationEvent) -> Unit,
    exchangeViewModel: ExchangeViewModel
) {
    var value by remember {
        mutableStateOf("450")
    }
    val context = LocalContext.current.applicationContext
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { newText ->
                value = newText.ifEmpty { 0.toString() }
            },
            label = { Text(text = "Ilość") },
            placeholder = { Text(text = "Ilość") },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        onEvent(DonationEvent.SetAmount(value.toInt()))
        val run = runBlocking {
            exchangeViewModel.saveAmount(value.toInt())
        }
    }
}