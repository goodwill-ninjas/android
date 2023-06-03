package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DonationApi {
    @GET("users/{userId}/donations")
    suspend fun userDonations(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Response<List<DonationResponse>>

    @POST("donations")
    suspend fun addDonation(
        @Header("Authorization") token: String,
        @Body donationBody: DonationBody
    ): Response<Int>?
}