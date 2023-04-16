package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation

sealed interface DonationEvent {
    object SaveDonation: DonationEvent
    data class SetDonatedType(val donatedType: String): DonationEvent
    data class SetDonationDate(val donationDate: Long): DonationEvent
    data class SetAmount(val amount: Int): DonationEvent
}