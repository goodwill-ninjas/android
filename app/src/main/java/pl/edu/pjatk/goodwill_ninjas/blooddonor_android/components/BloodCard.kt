package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.nextDonation.NextDonation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.BloodTypeIcon
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
@Composable
fun BloodCard (
    bloodType: String,
    donationDate: LocalDateTime,
    isNextDonationCard: Boolean,
    amount: Int
) {
    val bloodDonation = NextDonation(donationDate, bloodType)
    Box {
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Row {
                    Icon(
                        painter = painterResource(id = BloodTypeIcon.FullBlood),
                        contentDescription = null,
                        Modifier
                            .height(50.dp)
                    )
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(bloodType, fontWeight = FontWeight(700))
                        Text(text = "Następna donacja: ${donationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
                    }
                }
                if (isNextDonationCard) {
                    Box {
                        Text(
                            if(bloodDonation.calculateNextDonation() > 1)
                            stringResource(
                                id = R.string.next_donation,
                                bloodDonation.calculateNextDonation()
                            ) else stringResource(id = R.string.next_donation_now)
                        )
                    }
                }
                if (amount != 0) {
                    Box {
                        Text(text = stringResource(id = R.string.donation_card_amount, amount))
                    }
                }
            }
        }
    }
}