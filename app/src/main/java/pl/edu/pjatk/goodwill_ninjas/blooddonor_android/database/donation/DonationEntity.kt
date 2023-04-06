package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class DonationEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "donated_type") val donatedType : String,
    @ColumnInfo(name = "amount") val amount : Int,
    @ColumnInfo(name = "timestamp") val timestamp : LocalDateTime,
    )