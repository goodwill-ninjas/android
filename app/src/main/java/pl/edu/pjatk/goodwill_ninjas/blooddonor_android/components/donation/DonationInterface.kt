package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.DonationType
import java.time.LocalDateTime

interface DonationInterface {
    val donationDate: LocalDateTime;
    val donationType: DonationType;
    val amount: Int
}