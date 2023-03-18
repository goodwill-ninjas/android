package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.mainPage

//import org.joda.time.LocalDateTime
import java.time.LocalDateTime

interface NextDonationInterface {
    val date: LocalDateTime
    val type: DonationType
}