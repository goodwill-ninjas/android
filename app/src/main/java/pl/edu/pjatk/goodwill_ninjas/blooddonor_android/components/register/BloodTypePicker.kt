package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.register

import android.text.Selection
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.RegistrationEvent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BloodTypePicker(loginViewModel: LoginViewModel) {
    val options = listOf(
        "0 Rh-",
        "0 Rh+",
        "A Rh-",
        "A Rh+",
        "B Rh-",
        "B Rh+",
        "AB Rh-",
        "AB Rh+"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    Column {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Grupa krwi") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            loginViewModel.onEvent(RegistrationEvent.SetBloodType(selectionOption))
                            expanded = false
                        }
                    ){
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
}