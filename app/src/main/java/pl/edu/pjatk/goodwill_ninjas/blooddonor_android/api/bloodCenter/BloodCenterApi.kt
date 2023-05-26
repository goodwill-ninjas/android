package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenter

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BloodCenterApi {
    @GET("blood-centers")
    suspend fun bloodCenters(
        @Header("Authorization") token: String
    ): Response<List<BloodCenterResponse>>
}