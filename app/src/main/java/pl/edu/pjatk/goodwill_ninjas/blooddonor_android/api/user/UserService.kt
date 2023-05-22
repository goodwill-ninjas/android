package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class UserService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(UserApi::class.java)

    suspend fun successfulUserResponse(id: Int, token: String): UserResponse? {
        val userResponse = api.user(token, id)
        val successful = userResponse.isSuccessful
        val httpStatusCode = userResponse.code()
        val httpStatusMessage = userResponse.message()

        return userResponse.body()
    }
}