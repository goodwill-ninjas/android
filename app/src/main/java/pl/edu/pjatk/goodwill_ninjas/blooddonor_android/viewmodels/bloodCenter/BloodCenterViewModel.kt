package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.bloodCenter

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenter.BloodCenterService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenter
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenterDao

class BloodCenterViewModel(context: Context, token: String, private val dao: BloodCenterDao): ViewModel() {
    private val bloodCenters = dao.getAll()

    suspend fun getBloodCenters(): List<BloodCenter> {
        return bloodCenters.first()
    }

    fun editBloodCenter(bloodCenter: BloodCenter) {
        dao.updateBloodCenter(bloodCenter)
    }

    fun getBloodCenters(token: String) = runBlocking {
        val service = BloodCenterService()

        coroutineScope {
            val bloodCenters = getBloodCenters()
            Log.d("BloodCenters", bloodCenters.toString())
            if (bloodCenters.isEmpty()) {
                val beBloodCenters = service.successfulBloodCenterResponse(token)
                if (beBloodCenters != null) {
                    for (bloodCenter in beBloodCenters) {
                        dao.upsertBloodCenter(
                            BloodCenter(
                                id = bloodCenter.id,
                                name = bloodCenter.name,
                                streetName = bloodCenter.streetName,
                                postalCode = bloodCenter.postalCode,
                                city = bloodCenter.city,
                                voivodeship = bloodCenter.voivodeship,
                                geoCoordinates = bloodCenter.geoCoordinates,
                                phoneNumber = bloodCenter.phoneNumber,
                                openFrom = bloodCenter.openFrom,
                                openTo = bloodCenter.openTo,
                                createdAt = bloodCenter.createdAt
                            )
                        )
                    }
                }
            }
        }
    }
}