package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.util
import java.util.*
fun getDefaultDateInMillis(): Long {
    val cal = Calendar.getInstance()
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val date = cal.get(Calendar.DATE)
    cal.clear()
    cal.set(year, month, date)
    return cal.timeInMillis
}