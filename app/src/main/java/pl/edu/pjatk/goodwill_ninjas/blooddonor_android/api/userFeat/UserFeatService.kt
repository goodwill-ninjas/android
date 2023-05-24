package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.userFeat

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class UserFeatService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(UserFeatApi::class.java)

    suspend fun successfulUserFeatResponse(id: Int, token: String): List<UserFeatResponse>? {
        val userFeatResponse = api.userFeats("Bearer $token", id)
        val successful = userFeatResponse.isSuccessful
        val httpStatusCode = userFeatResponse.code()
        val httpStatusMessage = userFeatResponse.message()

        return userFeatResponse.body()
    }
}