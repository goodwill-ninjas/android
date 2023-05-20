package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.healthCheck

import android.content.Context
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class HealthCheckService (
    val context: Context
        ) {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(HealthCheckApi::class.java)

    suspend fun successfulHealthCheckResponse(token: String): String? {
        val healthCheckResponse = api.healthCheck(token)

        val successful = healthCheckResponse.isSuccessful
        val statusCode = healthCheckResponse.code()
        val httpStatusMessage = healthCheckResponse.message()

        return httpStatusMessage.toString()
    }
}