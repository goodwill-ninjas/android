package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.restClient

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {
    @Headers("Accept: Application/json")
    @GET("api/status")
    fun fetchHealthCheck(): Call<HealthCheckResponse>?
}

interface HealthCheckResponse {
    val status: Int
}