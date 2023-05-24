package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserFeat(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("userId") val userId: Int? = 0,
    @ColumnInfo("featId") val featId: String? = "",
    @ColumnInfo("featName") val featName: String? = "",
    @ColumnInfo("featDescription") val featDescription: String? = "",
    @ColumnInfo("achievedRanks") val achievedRanks: String? = "",
    @ColumnInfo("nextRanks") val nextRanks: String? = ""
)
