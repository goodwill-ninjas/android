package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Disqualification (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "companion_user_id") val companionUserId: Int?,
    @ColumnInfo(name ="date_start") val dateStart: Long? = 0,
    @ColumnInfo(name ="days") val days: Int? = 0,
    @ColumnInfo(name ="blood_pressure") val bloodPressure: String? = "",
    @ColumnInfo(name ="hemoglobin") val hemoglobin: Double? = null,
    @ColumnInfo(name ="details") val details: String? = "",
)