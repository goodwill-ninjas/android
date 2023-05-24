package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters

import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface BloodCenterDao {
    @Query("SELECT * from bloodCenter")
    fun getAll(): Flow<List<BloodCenter>>

    @Query("SELECT EXISTS (SELECT 1 FROM bloodCenter WHERE id = 1)")
    fun exists(): Boolean


    @Upsert
    suspend fun upsertBloodCenter(bloodCenter: BloodCenter)
}