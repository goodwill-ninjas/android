package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.healthCheck

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class HealthCheckService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(HealthCheckApi::class.java)

    suspend fun successfulHealthCheckResponse(): String? {
        val healthCheckResponse = api.healthCheck()

        val successful = healthCheckResponse.isSuccessful
        val statusCode = healthCheckResponse.code()
        val httpStatusMessage = healthCheckResponse.message()

        return healthCheckResponse.body()
    }
}