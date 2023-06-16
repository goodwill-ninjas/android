package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donationJournal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationParsers
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun DisqualificationCard(
    disqualificationDate: Long,
    days: Int
) {
    Box(modifier = Modifier.fillMaxWidth()){
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Row {
                    Icon(
                        Icons.Rounded.Error,
                        contentDescription = null,
                        Modifier.height(50.dp)
                    )
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(text = stringResource(id = R.string.disqualification), fontWeight = FontWeight.Bold)
                        Text(text = "Od: " +
                                Instant
                                    .ofEpochMilli(disqualificationDate)
                                    .atZone( ZoneId.of("Europe/Warsaw"))
                                    .toLocalDate()
                                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        )
                        Text(text = "Do: " +
                                Instant
                                    .ofEpochMilli(disqualificationDate + 86400000 * days)
                                    .atZone(ZoneId.of("Europe/Warsaw"))
                                    .toLocalDate()
                                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        )
                    }
                }
            }
        }
    }
}