package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.bloodCenter

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenter
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.ParseCapacityToIcons
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenter.BloodCenterViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenterDetailsViewModel.BloodCenterDetailsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BloodCenterCard (
    bloodCenter: BloodCenter,
    bloodCenterViewModel: BloodCenterViewModel,
    context: Context,
    token: String
) {
    var cardExpanded by remember { mutableStateOf(false) }
    val bloodCenterDetailsViewModel = BloodCenterDetailsViewModel(context, token)

    Box {
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                cardExpanded = !cardExpanded
                bloodCenter.city?.let { bloodCenterDetailsViewModel.getBloodCenterDetails(it, token) }
            }
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Row{
                    bloodCenter.name?.let { Text(text = it, fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 20.sp) }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column(Modifier.weight(1F)) {
                        Text(text = "Województwo:")
                    }
                    Column(Modifier.weight(1F)) {
                        bloodCenter.voivodeship?.let { Text(text = it) }
                    }
                }
                if(cardExpanded) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Column {
                        Row {
                            bloodCenter.streetName?.let { Text(text = "$it ") }
                            bloodCenter.streetNumber?.let { Text(text = it) }
                        }
                        Row {
                            bloodCenter.postalCode?.let { Text(text = "$it ") }
                            bloodCenter.city?.let { Text(text = it) }
                        }
                        Spacer(Modifier.height(5.dp))
                        Row {
                            Text(text = "Telefon:")
                            bloodCenter.phoneNumber?.let { Text(text = it) }
                        }
                        Column(modifier = Modifier.padding(5.dp)) {
                            Row {
                                Text(text = "Stany banku krwi:")
                            }
                            bloodCenterDetailsViewModel.getState().bloodCenterDetails.forEach {
                                Row {
                                    Column(Modifier.weight(1F)) {
                                        it.bloodType?.let { it1 -> Text(text = it1) }
                                    }
                                    Column(Modifier.weight(3F)) {
                                        it.capacity?.let { it1 -> ParseCapacityToIcons(capacity = it1) }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}