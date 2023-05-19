package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.healthCheck

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface HealthCheckApi {
    @Headers("Accepts: Application/json")
    @GET("status")
    suspend fun healthCheck(): Response<String>
}