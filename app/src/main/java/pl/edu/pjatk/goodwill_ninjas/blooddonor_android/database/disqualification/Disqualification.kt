package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Disqualification (
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo(name = "companion_user_id") val companionUserId: Int?,
    @ColumnInfo("date_start") val dateStart: Long? = 0L,
    @ColumnInfo("date_finish") val dateFinish: Long? = 0L,
    @ColumnInfo("blood_pressure") val bloodPressure: String? = "",
    @ColumnInfo("hemoglobin") val hemoglobin: Double? = 0.0,
    @ColumnInfo("details") val details: String? = "",
)