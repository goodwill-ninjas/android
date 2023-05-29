package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.Disqualification

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenter.BloodCenterApi
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenter.BloodCenterResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class DisqualificationService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(DisqualificationApi::class.java)

    suspend fun successfullDisqualificationResponse(token: String): List<DisqualificationResponse>? {
        val disqualificationResponse = api.disqualifications("Bearer $token")
        val successful = disqualificationResponse.isSuccessful
        val httpStatusCode = disqualificationResponse.code()
        val httpStatusMessage = disqualificationResponse.message()
        return disqualificationResponse.body()
    }


}