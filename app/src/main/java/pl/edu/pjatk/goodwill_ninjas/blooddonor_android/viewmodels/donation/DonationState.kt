package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation

data class DonationState(
    val donations: List<Donation> = emptyList(),
    val donated_type: String = "",
    val amount: Int = 0,
    val donation_date: Long = 0
)
