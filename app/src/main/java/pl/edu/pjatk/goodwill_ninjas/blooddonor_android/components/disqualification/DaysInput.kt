package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.disqualification

import android.util.Log
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
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.storeViewModel.ExchangeViewModel

@Composable
fun DaysInput(
    onDisqualificationEvent: (DisqualificationEvent) -> Unit,
    exchangeViewModel: ExchangeViewModel
) {
    var value by remember {
        mutableStateOf("0")
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
            label = { Text(text = "Ilość dni dyskwalifikacji") },
            placeholder = { Text(text = "Ilość") },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        onDisqualificationEvent(DisqualificationEvent.SetDays(value.toInt()))
        runBlocking {
            exchangeViewModel.saveDisqualificationDays(value.toInt())
            Log.d("exchange", exchangeViewModel.getDisqualificationDays().toString())
        }
    }
}