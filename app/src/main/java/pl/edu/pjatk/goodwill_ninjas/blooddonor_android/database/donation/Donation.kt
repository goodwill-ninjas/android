package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Donation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "donated_type") val donatedType: String,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "donation_date") val donationDate: Long
    )