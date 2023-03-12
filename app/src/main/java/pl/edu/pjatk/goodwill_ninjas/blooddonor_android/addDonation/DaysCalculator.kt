package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import java.time.LocalDate
import java.time.Period

fun DaysCalculator() {
    var today = LocalDate.now()
    //hardcoded theoretical date
    var lastDonation = LocalDate.parse("2023-2-20")
    var nextDonation = LocalDate.parse("2023-2-20").plusDays(57)
    var periodDays = Period.between(nextDonation, today)
}