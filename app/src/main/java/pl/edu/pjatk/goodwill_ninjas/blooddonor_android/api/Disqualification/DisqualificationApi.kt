package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.Disqualification

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenter.BloodCenterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface DisqualificationApi {
    @GET("disqualification")
    suspend fun disqualifications(
        @Header("Authorization") token: String
    ): Response<List<DisqualificationResponse>>
}