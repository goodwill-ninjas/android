package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenterDetailsViewModel

import android.content.Context
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenterDetails.BloodCenterDetailsResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenterDetails.BloodCenterDetailsService

class BloodCenterDetailsViewModel(context: Context, token: String) {
    private val state = MutableStateFlow(BloodCenterDetailsResponse())

    fun getState(): BloodCenterDetailsResponse {
        return state.value
    }

    fun getBloodCenterDetails(city: String, token: String) = runBlocking {
        val service = BloodCenterDetailsService()

        coroutineScope {
            val bloodCenterDetails = service.successfulBloodCenterDetailsResponse(city, token)
            Log.d("BloodCenterDetails", bloodCenterDetails.toString())
            if (bloodCenterDetails != null) {
                state.update { bloodCenterDetails }
            }
        }
    }
}