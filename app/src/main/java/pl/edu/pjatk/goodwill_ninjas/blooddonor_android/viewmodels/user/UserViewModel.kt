package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UserViewModel(private val context: Context, tokenBody: String) {
    private val userIdStore = UserIdStore(context)
    private val userNameStore = UserNameStore(context)
    private val gson = Gson()
    private val serializedToken = gson.fromJson<UserTokenData>(tokenBody, UserTokenData::class.java)

    fun setUserId() {
        runBlocking {
            coroutineScope {
                userIdStore.saveUserId(serializedToken.context.user.userId)
            }
        }
    }

    fun getUserId() = runBlocking {
        userIdStore.getUserId
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
}