package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.addDonation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.BloodPressureInput
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.BloodQtyInput
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.HemoglobinLevelInput
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.storeViewModel.ExchangeViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.rememberImeState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationState

@Composable
fun AdvancedDonationParams(onEvent: (DonationEvent) ->Unit, donationState:DonationState, exchangeViewModel: ExchangeViewModel, onDisqualificationEvent: (DisqualificationEvent)->Unit) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val imeState = rememberImeState()
    LaunchedEffect(key1 = imeState.value){
        if(imeState.value){
            scrollState.scrollTo(scrollState.maxValue)
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        ) {
        val image = painterResource(id = R.drawable.droplet)
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding()) {
                Image(painter = image, contentDescription = null, Modifier.height(250.dp))
            }
            Box(modifier = Modifier.padding()) {
                androidx.compose.material.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clickable { },
                    elevation = 10.dp
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Row {
                            Text(
                                text = "Krew pełna", fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row {
                            BloodPressureInput(onEvent, onDisqualificationEvent)
                        }
                        Row {
                            HemoglobinLevelInput(onEvent, onDisqualificationEvent)
                        }
                        Row {
                            ExaminationResult(onEvent)
                        }
                        Row {
                            DonationHandSelector(onEvent)
                        }
                        Row {
                            BloodQtyInput(onEvent, exchangeViewModel)
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun ExaminationResult(onEvent: (DonationEvent) -> Unit) {
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
            label = { Text(text = "Podaj wynik badania") },
            placeholder = { Text(text = "podaj wynik badania") },
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.None
            ),
        )
        onEvent(DonationEvent.SetDetails(value))
    }
}
@Composable
fun DonationHandSelector(onEvent: (DonationEvent) -> Unit) {
    val options = listOf("Lewa", "Prawa")
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf("Lewa")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Box {
            TextButton(onClick = { isExpanded = true }) {
                Row {
                    Text(text = "$selectedOption", fontSize = 20.sp)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
            }
            DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                options.forEach {
                    DropdownMenuItem(onClick = {
                        isExpanded = false
                        selectedOption = it
                    }) {
                        Text(text = it)
                    }
                }

            }
            onEvent(DonationEvent.SetHand(selectedOption))
        }
    }
}