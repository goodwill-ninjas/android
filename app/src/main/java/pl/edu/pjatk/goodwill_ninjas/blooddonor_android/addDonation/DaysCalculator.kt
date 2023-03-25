package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import java.time.LocalDate
import java.time.Period

<<<<<<< HEAD
fun DaysCalculator() {
=======
class DaysCalculator {
>>>>>>> 53aedfa7b89f859bde3407356caba4ce85e436f6
    var today = LocalDate.now()

    //hardcoded theoretical date
    var lastDonation = LocalDate.parse("2023-2-20")
    var nextDonation = LocalDate.parse("2023-2-20").plusDays(57)
    var periodDays = Period.between(nextDonation, today)
}