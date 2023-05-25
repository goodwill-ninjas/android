package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenterDetails

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BloodCenterDetailsApi {
    @GET("blood-centers/{city}")
    suspend fun getBloodCenterDetails(
        @Header("Authorization") token: String,
        @Path("city") city: String
    ): Response<BloodCenterDetailsResponse>
}