package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.healthCheck

import android.content.Context
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.healthCheck.HealthCheckService

class HealthCheckViewModel (private val context: Context) {
    private val token = ""
    fun healthCheck() = runBlocking {
        val service = HealthCheckService()
        coroutineScope {
            Log.d("healthCheckResponse", service.successfulHealthCheckResponse().toString())
        }
    }
}