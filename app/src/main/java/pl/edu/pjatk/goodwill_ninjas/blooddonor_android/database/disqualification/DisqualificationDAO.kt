package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface DisqualificationDAO {
    @Query("SELECT * from disqualification")
    fun getAll(): Flow<List<Disqualification>>
    @Update
    fun updateDisqualification(disqualification: Disqualification)
    @Upsert
    suspend fun upsertDisqualification(disqualification: Disqualification)
}