package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.nextDonation

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.DonationType
import java.time.temporal.ChronoUnit
import java.time.LocalDateTime

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