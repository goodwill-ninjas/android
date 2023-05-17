package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {
    @Headers("Accept: Application/json")
    @POST("auth/login")
    suspend fun login(@Body loginBody: LoginBody): Response<LoginResponse>?
}
