package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.Disqualification

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class DisqualificationResponse (
    @JsonProperty("id") val id: Int = 0,
    @JsonProperty("date_start") val dateStart: Long? = 0L,
    @JsonProperty("date_finish") val dateFinish: Long? = 0L,
    @JsonProperty("blood_pressure") val bloodPressure: String? = "",
    @JsonProperty("hemoglobin") val hemoglobin: Double? = 0.0,
    @JsonProperty("details") val details: String? = "",
)