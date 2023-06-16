package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.disqualification

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent

@Composable
fun DisqualificationDetails(onEventDisqualification: (DisqualificationEvent) -> Unit) {
    var value by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .height(150.dp)
                .height(IntrinsicSize.Min),
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            label = { Text(text = "Podaj przyczynę dyskwalifikacji") },
            placeholder = { Text(text = "Podaj przyczynę dyskwalifikacji") },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
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

        onEventDisqualification(DisqualificationEvent.SetDetails(value))
    }
}