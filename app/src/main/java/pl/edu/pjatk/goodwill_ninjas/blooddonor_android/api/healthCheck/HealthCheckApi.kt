package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.healthCheck

import android.content.Context
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.healthCheck.HealthCheckViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.TokenStore
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface HealthCheckApi {
    @GET("status")
    suspend fun healthCheck(
        @Header("Authorization") token: String
    ): Response<String>
}