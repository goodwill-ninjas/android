package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.UserCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.nextDonation.NextDonation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.DonationType

@Composable
fun DonationJournal (
    name: String,
    donations: List<Donation>
) {
    val scrollableState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)
    ) {
        UserCard(name = name, badgeLevel = 1, donatedBlood = 12500)
        Spacer(modifier = Modifier.height(20.dp))
        Column(Modifier.verticalScroll(scrollableState)) {
            donations.forEach {donation ->
                BloodCard(bloodType = donation.donationType , isNextDonationCard = false, amount = donation.amount, donationDate = donation.donationDate)
            }
        }
    }
}