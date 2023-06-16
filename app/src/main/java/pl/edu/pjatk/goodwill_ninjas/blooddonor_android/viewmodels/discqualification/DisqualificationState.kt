package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.discqualification

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.Disqualification

data class DisqualificationState(
    val disqualifications: List<Disqualification> = emptyList(),
    val companionUserId: Int? = null,
    val dateStart: Long? = null,
    val days: Int? = null,
    val bloodPressure: String? = null,
    val hemoglobin: Double? = null,
    val details: String? = null,
)