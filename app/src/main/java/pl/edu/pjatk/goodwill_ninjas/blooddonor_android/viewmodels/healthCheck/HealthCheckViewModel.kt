package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.healthCheck

import android.content.Context
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.healthCheck.HealthCheckService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel

class HealthCheckViewModel (private val context: Context) {
    fun healthCheck() = runBlocking {
        val service = HealthCheckService(context)
        val token = LoginViewModel(context).getToken()
        coroutineScope {
            Log.d("healthCheckResponse", service.successfulHealthCheckResponse(token).toString())
        }
    }
}