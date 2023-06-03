package pl.edu.pjatk.goodwill_ninjas.blooddonor_android

import org.junit.Test
import org.junit.Assert.*
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.nextDonation.NextDonation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.DonationType
import java.time.LocalDateTime

class NextDonationTestBody {
    private val nextDonation = NextDonation(LocalDateTime.now(), DonationType.FULL)
    private val nextDonationLongTimeAgo = NextDonation(LocalDateTime.now().minusDays(365), DonationType.FULL)
    @Test
    fun testCalculateNextDonation() {
        assertTrue(nextDonation.calculateNextDonation() > 84)
    }
    @Test
    fun testCalculateNextDonationForLongTimeAgo() {
        assertTrue(nextDonationLongTimeAgo.calculateNextDonation() < 0)
    }
}