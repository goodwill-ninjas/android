package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.storeViewModel.ExchangeViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenter.BloodCenterViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel

@Composable
fun DropDownMenuBloodCentre(
    onEvent: (DonationEvent) -> Unit,
    exchangeViewModel: ExchangeViewModel,
    context: Context, db: AppDatabase
) {
    var bloodCenterViewModel: BloodCenterViewModel
    val loginViewModel = LoginViewModel(context)
    val token = loginViewModel.getToken()
    bloodCenterViewModel = BloodCenterViewModel(context, token, db.bloodCenterDao())
    val bc = bloodCenterViewModel.getBloodCenters(token)
    val bloodCenters = runBlocking {
        bloodCenterViewModel.getBloodCenters().map { item -> item.name }
    }
    Log.d("bc", bc.toString())
    BlooddonorandroidTheme {
        val options = listOf(
            "RCKiK Gdańsk",
            "RCKiK Warszawa",
            "RCKiK Poznań",
            "RCKiK Kraków"
        )
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var selectedOption by remember {
            mutableStateOf("RCKiK Gdańsk")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box {
                TextButton(onClick = { isExpanded = true }) {
                    Row {
                        Text(text = selectedOption, fontSize = 20.sp)
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                }
                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    bloodCenters.forEach {
                        DropdownMenuItem(onClick = {
                            isExpanded = false
                            if (it != null) {
                                selectedOption = it
                            }
                        }) {
                            if (it != null) {
                                Text(text = it, fontSize = 20.sp)
                            }

                        }
                    }
                }
                onEvent(DonationEvent.SetBloodCenter(selectedOption))
                val run = runBlocking {
                    exchangeViewModel.saveBloodCentre(selectedOption)
                }
            }
        }
    }
}

