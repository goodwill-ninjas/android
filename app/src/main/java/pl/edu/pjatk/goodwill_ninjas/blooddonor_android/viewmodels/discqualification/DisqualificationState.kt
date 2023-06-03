package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.discqualification

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.Disqualification
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation

data class DisqualificationState (
    val disqualifications: List<Disqualification> = emptyList(),
    val companionUserId: Int? = null,
    val dateStart: Long? = null,
    val days: Int? = null,
    val bloodPressure: String? = null,
    val hemoglobin: String? = null,
    val details: String? = null,
)