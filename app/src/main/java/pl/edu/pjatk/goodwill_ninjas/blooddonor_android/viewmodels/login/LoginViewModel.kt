package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login

import android.content.Context
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login.LoginService

class LoginViewModel(private val context: Context) {
    private val store = TokenStore(context)
    fun login(email: String, password: String) = runBlocking {
        val service = LoginService()
        coroutineScope {
            val token = service.successfulLoginResponse(email, password)
            store.saveToken("Bearer $token")
        }
    }

    fun logout() = runBlocking {
        coroutineScope {
            store.saveToken("")
        }
    }

    fun getToken() = runBlocking {
        store.getAccessToken.first()
    }
}
