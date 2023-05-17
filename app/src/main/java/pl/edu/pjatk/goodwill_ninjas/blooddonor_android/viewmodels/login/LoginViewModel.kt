package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login

import android.content.Context
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login.LoginService

class LoginViewModel(private val context: Context) {
    private val store = TokenStore(context)
    fun login() = runBlocking {
        val service = LoginService()
        coroutineScope {
            val token = service.successfulLoginResponse()
            store.saveToken(token)
        }
    }

    fun logout() = runBlocking {
        store.saveToken("")
    }

    fun getToken() = store.getAccessToken
}
