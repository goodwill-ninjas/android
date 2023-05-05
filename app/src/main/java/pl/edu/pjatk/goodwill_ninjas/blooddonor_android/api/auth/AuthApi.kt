package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.auth

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.ApiConstants
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.auth.model.Auth
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST(ApiConstants.ENDPOINTS.auth)
    suspend fun registerUser(
        @Body user: Auth
    ): Response<Auth>
}