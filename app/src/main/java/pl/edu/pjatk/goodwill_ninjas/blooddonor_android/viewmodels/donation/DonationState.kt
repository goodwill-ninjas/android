package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation


data class DonationState(
    val donations: List<Donation> = emptyList(),
    val companionUserId: Int? = null,
    val donatedType: String = "",
    val amount: Int = 0,
    val bloodPressure: String? = null,
    val hemoglobin: Double? = null,
    val details: String? = null,
    val createdAt: Long? = null,
    val deletedAt: Long? = null
)
