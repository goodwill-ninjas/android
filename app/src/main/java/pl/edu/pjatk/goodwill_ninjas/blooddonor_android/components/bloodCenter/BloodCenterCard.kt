package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.bloodCenter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenter

@Composable
fun BloodCenterCard (
    bloodCenter: BloodCenter
) {
    Box {
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Row{
                    Text(text = bloodCenter.name)
                }
                Row {
                    Text(text = "Miasto:")
                    Text(text = bloodCenter.city)
                }
                Row {
                    Text(text = "Adres:")
                    Text(text = bloodCenter.streetName)
                }
                Row {
                    Text(text = "Województwo:")
                    Text(text = bloodCenter.voivodeship)
                }
                Row {
                    Text(text = "Telefon:")
                    Text(text = bloodCenter.phoneNumber)
                }
            }
        }
    }
}