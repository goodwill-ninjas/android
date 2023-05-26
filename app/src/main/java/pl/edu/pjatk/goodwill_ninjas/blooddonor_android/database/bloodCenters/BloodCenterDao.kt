package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodCenterDao {
    @Query("SELECT * from bloodCenter")
    fun getAll(): Flow<List<BloodCenter>>

    @Update
    fun updateBloodCenter(bloodCenter: BloodCenter)

    @Upsert
    suspend fun upsertBloodCenter(bloodCenter: BloodCenter)
}