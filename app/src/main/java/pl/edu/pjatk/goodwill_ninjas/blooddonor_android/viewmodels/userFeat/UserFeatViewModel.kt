package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.userFeat

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.userFeat.UserFeatResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.userFeat.UserFeatService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeat
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeatDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.userTokenData.UserTokenData

class UserFeatViewModel(context: Context, token: String, private val dao: UserFeatDao): ViewModel() {
    private val userViewModel = UserViewModel(context, token)
    private val id: Int = userViewModel.getUserId()

    val feats = dao.getAll()

    fun getUserFeats(feats: List<UserFeat>, token: String) = runBlocking {
        val service = UserFeatService()
        coroutineScope {
            val userFeats = service.successfulUserFeatResponse(id, token);
            if (userFeats != null) {
                if(userFeats.size != feats.size) {
                    for (feat in userFeats) {
                        val userFeat = UserFeat(
                            featId = feat.featId,
                            userId = feat.userId,
                            featName = feat.featName,
                            featDescription = feat.featDescription,
                            achievedRanks = feat.achievedRanks,
                            nextRanks = feat.nextRanks,
                        )
                        if (!dao.exists(feat.featId, feat.featName, feat.achievedRanks)) dao.upsertUserFeat(userFeat)
                    }
                }
                    Log.d("UserFeats", userFeats.toString())
            }
        }
    }
}