package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation


sealed interface DonationEvent {
    object SaveDonation: DonationEvent
    data class SetCompanionUserId(val companionUserId: Int): DonationEvent
    data class SetDonatedType(val donatedType: String): DonationEvent
    data class SetAmount(val amount: Int): DonationEvent
    data class SetBloodPressure(val bloodPressure: String): DonationEvent
    data class SetHemoglobin(val hemoglobin: Double): DonationEvent
    data class SetDetails(val details: String): DonationEvent
    data class SetCreatedAt(val createdAt: Long): DonationEvent
    data class SetDeletedAt(val deletedAt: Long): DonationEvent
    data class SetHand(val hand: String): DonationEvent
    data class SetBloodCenter(val bloodCenter: String): DonationEvent
}