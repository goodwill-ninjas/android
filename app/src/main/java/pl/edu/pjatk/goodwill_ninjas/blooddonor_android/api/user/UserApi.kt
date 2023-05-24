package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserApi {
    @GET("users/{userId}")
    suspend fun user(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Response<UserResponse>
}