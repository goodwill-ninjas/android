package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.nextDonation

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.DonationType
import java.time.LocalDateTime

interface NextDonationInterface {
    val date: LocalDateTime
    val type: String
}