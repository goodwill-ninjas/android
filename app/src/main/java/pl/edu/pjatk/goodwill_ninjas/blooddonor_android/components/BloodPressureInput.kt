package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BloodPressureInput() {
    var value by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        TextField(
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            label = { androidx.compose.material3.Text(text = "Ciśnienie") },
            placeholder = { Text(text = "00:00") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search
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
    }
}