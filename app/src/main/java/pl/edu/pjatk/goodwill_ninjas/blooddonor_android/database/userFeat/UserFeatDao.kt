package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserFeatDao {
    @Query("SELECT * FROM userFeat")
    fun getAll(): Flow<List<UserFeat>>

    @Delete
    fun deleteAll(userFeats: List<UserFeat>)

    @Query("SELECT EXISTS (SELECT 1 FROM userFeat WHERE featId = :featId AND featName = :featName AND achievedRanks = :achievedRanks)")
    fun exists(featId: String, featName: String, achievedRanks: String): Boolean

    @Upsert
    suspend fun upsertUserFeat(userFeat: UserFeat)
}