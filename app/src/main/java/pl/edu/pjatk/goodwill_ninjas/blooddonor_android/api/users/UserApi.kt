package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.users.model.User
import retrofit2.http.GET

interface UserApi {
    @GET(ApiConstants.ENDPOINTS.users)
    suspend fun getUser(): User
}