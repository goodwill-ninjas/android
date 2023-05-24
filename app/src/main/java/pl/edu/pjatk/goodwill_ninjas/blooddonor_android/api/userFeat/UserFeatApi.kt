package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.userFeat

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserFeatApi {
    @GET("users/{userId}/feats")
    suspend fun userFeats(
            @Header("Authorization") token: String,
            @Path("userId") userId: Int
        ): Response<List<UserFeatResponse>>
}