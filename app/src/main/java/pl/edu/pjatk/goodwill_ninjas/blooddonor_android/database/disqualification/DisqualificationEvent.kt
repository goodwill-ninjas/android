package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
sealed interface DisqualificationEvent{
    object SaveDisqualification: DisqualificationEvent
    data class SetCompanionUserId(val companionUserId: Int): DisqualificationEvent
    data class SetDateStart(val dateStart: Long): DisqualificationEvent
    data class SetDays(val days: Int): DisqualificationEvent
    data class SetBloodPressure(val bloodPressure: String): DisqualificationEvent
    data class SetHemoglobin(val hemoglobin: String): DisqualificationEvent
    data class SetDetails(val details: String): DisqualificationEvent
}