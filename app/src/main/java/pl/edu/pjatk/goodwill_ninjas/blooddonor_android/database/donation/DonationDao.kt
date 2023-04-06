package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation

import androidx.room.Dao
import androidx.room.Query
@Dao
interface DonationDao {
    @Query("SELECT * FROM donation")
    fun getAll(): List<DonationEntity>
}