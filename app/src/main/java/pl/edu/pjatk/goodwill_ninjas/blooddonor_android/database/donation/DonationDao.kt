package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DonationDao {
    @Query("SELECT * FROM donation")
    fun getAll(): Flow<List<Donation>>

    @Upsert
    suspend fun upsertDonation(donation: Donation)
}