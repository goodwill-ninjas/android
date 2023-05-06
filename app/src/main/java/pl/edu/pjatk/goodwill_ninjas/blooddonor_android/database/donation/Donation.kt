package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Donation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "companion_user_id") val companionUserId: Int?,
    @ColumnInfo(name = "donated_type") val donatedType: String,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "blood_pressure") val bloodPressure: String?,
    @ColumnInfo(name = "hemoglobin") val hemoglobin: Double?,
    @ColumnInfo(name = "details") val details: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long?,
    @ColumnInfo(name = "deleted_at") val deletedAt: Long?
    )