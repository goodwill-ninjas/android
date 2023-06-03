package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat
import java.text.SimpleDateFormat

class DonationParsers {
    fun parseDonationType(donationType: String): String {
        if (donationType == "Krew pełna") return "whole"
        if (donationType == "Osocze") return "plasma"
        if (donationType == "Krwinki czerwone") return "power"
        if (donationType == "Płytki krwi") return "platelet"
        if (donationType == "whole") return "Krew pełna"
        if (donationType == "plasma") return "Osocze"
        if (donationType == "power") return "Krwinki czerwone"
        if (donationType == "platelet") return "Płytki krwi"
        return ""
    }

    fun parseToDate(unixTimestamp: Long): String {
        val date = DateTime(unixTimestamp)
        return date.toDateTimeISO().toString()
    }

    fun parseHand(hand: String?): String? {
        if (hand == "Lewa") return "left"
        if (hand == "Prawa") return "right"
        return null
    }

    fun parseHemoglobin(hemoglobin: Double?): String? {
        if (hemoglobin != null && hemoglobin > 0.0) {
            return hemoglobin.toString()
        }
        return null
    }
}