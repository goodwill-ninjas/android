package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.userFeat

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.userFeat.UserFeatService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeat
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeatDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import kotlin.coroutines.coroutineContext

class UserFeatViewModel(context: Context, token: String, private val dao: UserFeatDao): ViewModel() {
    private val userViewModel = UserViewModel(context, token)
    private val id: Int = userViewModel.getUserId()

    private val feats = dao.getAll()

    private suspend fun getFeats(): List<UserFeat> {
        return feats.first()
    }

    fun getUserFeats(token: String) = runBlocking {
        val service = UserFeatService()
            dao.deleteAll()
        coroutineScope {
            val userFeats = service.successfulUserFeatResponse(id, token);
            if (userFeats != null) {
                    for (feat in userFeats) {
                        val userFeat = UserFeat(
                            featId = feat.featId,
                            userId = feat.userId,
                            featName = feat.featName,
                            featDescription = feat.featDescription,
                            achievedRanks = feat.achievedRanks.map { item -> item.id }.toString(),
                            nextRanks = feat.nextRanks.map { item -> item.id }.toString(),
                        )
                        dao.upsertUserFeat(userFeat)
                    }
                Log.d("UserFeats", userFeats.toString())
            }
        }
    }
}