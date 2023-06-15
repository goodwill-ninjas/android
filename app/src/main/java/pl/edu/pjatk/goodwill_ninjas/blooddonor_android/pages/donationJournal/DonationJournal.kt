package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.donationJournal

import android.content.Context
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Database
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user.UserResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.BloodCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.UserCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.nextDonation.NextDonation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.DonationType
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Composable
fun DonationJournal (
    name: String,
    state: DonationState,
    onEvent: (DonationEvent) -> Unit,
    db: AppDatabase,
    context: Context,
    userId: Int,
    token: String,
    navController: NavController
) {
    val donationViewModel = DonationViewModel(dao = db.donationDao(), context = context)
    lateinit var userViewModel: UserViewModel
    var user: UserResponse

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)
    ) {
        if(token.isEmpty()) {
            navController.navigate(Routes.PROFILE)
        } else {
            donationViewModel.getDonations(userId, token)
            userViewModel = UserViewModel(context, token)
            runBlocking {
                userViewModel.getUser(userId, token)
                user = userViewModel.state.value
            }
            val scrollableState = rememberScrollState()
            UserCard(user, context)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Historia Donacji", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Column(Modifier.verticalScroll(scrollableState)) {
                state.donations.sortedBy { item -> item.createdAt }
                state.donations.forEach { donation ->
                    BloodCard(bloodType = donation.donatedType,
                        isNextDonationCard = false,
                        amount = donation.amount,
                        donationDate = LocalDateTime.ofInstant(
                            donation.createdAt?.let { Instant.ofEpochMilli(it) },
                            TimeZone.getTimeZone("CEST").toZoneId()
                        )
                    )
                }
            }
            if (state.donations.isEmpty()) {
                Text(text = "Nie dodałeś jeszcze donacji. Oddaj krew już dziś!")
            }
        }
    }
}