package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.bloodCenter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenter
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenter.BloodCenterViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BloodCenterCard (
    bloodCenter: BloodCenter,
    bloodCenterViewModel: BloodCenterViewModel
) {
    var cardExpanded by remember { mutableStateOf(false) }
    Box {
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = { cardExpanded = !cardExpanded }
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Row{
                    bloodCenter.name?.let { Text(text = it) }
                }
                Row {
                    Text(text = "Województwo:")
                    bloodCenter.voivodeship?.let { Text(text = it) }
                }
                if(cardExpanded) {
                    Column {
                        Row {
                            bloodCenter.streetName?.let { Text(text = "$it ") }
                            bloodCenter.streetNumber?.let { Text(text = it) }
                        }
                        Row {
                            bloodCenter.postalCode?.let { Text(text = "$it ") }
                            bloodCenter.city?.let { Text(text = it) }
                        }
                        Row {
                            Text(text = "Telefon:")
                            bloodCenter.phoneNumber?.let { Text(text = it) }
                        }
                        Column(modifier = Modifier.padding(5.dp)) {

                        }
                    }
                }
            }
        }
    }
}