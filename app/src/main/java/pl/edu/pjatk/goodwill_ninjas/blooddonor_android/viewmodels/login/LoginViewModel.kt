package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login

import android.content.Context
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login.LoginService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login.RegisterBody

class LoginViewModel(private val context: Context) {
    private val store = TokenStore(context)

    val registrationDataStore = MutableStateFlow(RegisterBody())
    fun login(email: String, password: String) = runBlocking {
        val service = LoginService()
        coroutineScope {
            val token = service.successfulLoginResponse(email, password)
            if (token.isNotEmpty()) {
                store.saveToken(token)
            } else {
                store.saveToken("")
            }
        }
    }

    fun register(
        body: RegisterBody
    ) = runBlocking {
        val service = LoginService()
        coroutineScope {
            val responseCode = service.successfulRegisterResponse(body)
        }
    }

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.SetEmail -> {
                registrationDataStore.update {
                    it.copy( email = event.email )
                }
            }
            is RegistrationEvent.SetPassword -> {
                registrationDataStore.update {
                    it.copy( password = event.password )
                }
            }
            is RegistrationEvent.SetUsername -> {
                registrationDataStore.update {
                    it.copy( username = event.username )
                }
            }
            is RegistrationEvent.SetBloodType -> {
                registrationDataStore.update {
                    it.copy( blood_type = event.blood_type )
                }
            }
            is RegistrationEvent.SetGender -> {
                registrationDataStore.update {
                    it.copy( gender = event.gender )
                }
            }
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
