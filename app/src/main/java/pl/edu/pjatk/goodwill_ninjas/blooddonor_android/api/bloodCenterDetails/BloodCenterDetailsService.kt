package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenterDetails

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class BloodCenterDetailsService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(BloodCenterDetailsApi::class.java)

    suspend fun successfulBloodCenterDetailsResponse(city: String, token: String): BloodCenterDetailsResponse? {
        val bloodCenterDetailsResponse = api.getBloodCenterDetails("Bearer $token", city)
        val successful = bloodCenterDetailsResponse.isSuccessful
        val httpStatusCode = bloodCenterDetailsResponse.code()
        val httpStatusMessage = bloodCenterDetailsResponse.message()

        return bloodCenterDetailsResponse.body()
    }
}