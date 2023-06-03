package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

import org.joda.time.DateTime

class DonationParsers {
    fun parseDonationType(donationType: String): String {
        if (donationType == "Krew pełna") return "whole"
        if (donationType == "Osocze") return "plasma"
        if (donationType == "Krwinki czerwone") return "power"
        if (donationType == "Płytki krwi") return "platelet"
        return ""
    }

    fun parseToDate(unixTimestamp: Long): String {
        val date = DateTime(unixTimestamp)
        return date.toDateTimeISO().toString()
    }
}