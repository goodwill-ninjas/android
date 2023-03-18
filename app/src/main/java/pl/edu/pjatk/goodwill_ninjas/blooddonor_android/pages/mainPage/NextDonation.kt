package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

import java.time.temporal.ChronoUnit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NextDonation(
    override val date: LocalDateTime,
    override val type: DonationType,
) : NextDonationInterface {
    fun calculateNextDonation(): Long {
        return if (this.type == DonationType.FULL) {
            ChronoUnit.DAYS.between(LocalDateTime.now(), date.plusMonths(3))
        } else {
            ChronoUnit.DAYS.between(LocalDateTime.now(), date.plusMonths(2))
        }
    }

}


enum class DonationType {
    FULL,
    BLOOD_CELLS
}
