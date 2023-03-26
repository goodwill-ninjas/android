package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donation

import java.time.LocalDateTime

data class Donation (val donationType: String, val amount: Int, val donationDate: LocalDateTime)