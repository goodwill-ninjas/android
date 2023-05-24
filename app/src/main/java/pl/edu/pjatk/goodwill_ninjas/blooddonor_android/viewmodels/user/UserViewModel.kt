package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user.UserResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user.UserService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.JWTUtils
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.userBackendData.UserEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.userTokenData.UserIdStore
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.userTokenData.UserNameStore
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.userTokenData.UserTokenData

class UserViewModel(context: Context, tokenBody: String): ViewModel() {

//    Values for getting data from JWT
    private val userIdStore = UserIdStore(context)
    private val userNameStore = UserNameStore(context)
    private val gson = Gson()

    private val serializedToken: UserTokenData = gson.fromJson<UserTokenData>(JWTUtils().decoded(tokenBody), UserTokenData::class.java)

//    Values for getting data from backend and keeping them in local state
    val state = MutableStateFlow(UserResponse())

    fun setUserId() {
        runBlocking {
            coroutineScope {
                userIdStore.saveUserId(serializedToken.context.user.userId)
            }
        }
    }

    fun getUserId() = runBlocking {
        userIdStore.getUserId.first()
    }

    fun setUserName() {
        runBlocking {
            coroutineScope {
                userNameStore.saveUserName(serializedToken.context.user.displayName
                )
            }
        }
    }

    fun getUserName() = runBlocking {
        userNameStore.getUserName.first()
    }

    private fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.SetUser -> {
                state.update { event.user }
            }
        }
    }

    fun getUser(id: Int, token: String) = runBlocking {
        val service = UserService()
        coroutineScope {
            val user = service.successfulUserResponse(id, "Bearer $token")
            if (user != null) onEvent(UserEvent.SetUser(user))
        }
    }
}