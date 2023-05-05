package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.auth

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.auth.model.Auth
import retrofit2.Response

class AuthRepository {
    suspend fun register(user: Auth): Response<Auth> {
        RetrofitInstance.api
    }
}