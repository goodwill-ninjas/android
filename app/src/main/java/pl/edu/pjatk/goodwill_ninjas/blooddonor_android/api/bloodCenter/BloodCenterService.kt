package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenter

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class BloodCenterService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(BloodCenterApi::class.java)

    suspend fun successfulBloodCenterResponse(token: String): List<BloodCenterResponse>? {
        val bloodCenterResponse = api.bloodCenters("Bearer $token")
        val successful = bloodCenterResponse.isSuccessful
        val httpStatusCode = bloodCenterResponse.code()
        val httpStatusMessage = bloodCenterResponse.message()
        return bloodCenterResponse.body()
    }
}