package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.discqualification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.Disqualification
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationDAO
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent

class DisqualificationViewModel(private val dao: DisqualificationDAO) : ViewModel() {
    private val _state = MutableStateFlow(DisqualificationState())
    private val _disqualifications = dao.getAll()
    val state = combine(_state, _disqualifications) { state, disqualifications ->
        state.copy(
            disqualifications = disqualifications
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DisqualificationState())

    fun onEventDisqualification(event: DisqualificationEvent) {
        when (event) {
            is DisqualificationEvent.SaveDisqualification -> {
                val companionUserId = state.value.companionUserId
                val dateStart = state.value.dateStart
                val days = state.value.days
                val bloodPressure = state.value.bloodPressure
                val hemoglobin = state.value.hemoglobin
                val details = state.value.details
                if (dateStart == null || days == null) {
                    return
                }
                val disqualification = Disqualification(
                    companionUserId = companionUserId,
                    dateStart = dateStart,
                    days = days,
                    bloodPressure = bloodPressure,
                    hemoglobin = hemoglobin,
                    details = details,
                )
                viewModelScope.launch {
                    dao.upsertDisqualification(disqualification)
                }
                _state.update {
                    it.copy(
                        companionUserId = null,
                        dateStart = 0,
                        days = 0,
                        bloodPressure = null,
                        hemoglobin = null,
                        details = "",
                    )
                }
            }
            is DisqualificationEvent.SetCompanionUserId -> {
                _state.update {
                    it.copy(
                        companionUserId = event.companionUserId
                    )
                }
            }
            is DisqualificationEvent.SetDateStart -> {
                _state.update {
                    it.copy(
                        dateStart = event.dateStart
                    )
                }
            }
            is DisqualificationEvent.SetDays -> {
                _state.update {
                    it.copy(
                        days = event.days
                    )
                }
            }
            is DisqualificationEvent.SetBloodPressure -> {
                _state.update {
                    it.copy(
                        bloodPressure = event.bloodPressure
                    )
                }
            }
            is DisqualificationEvent.SetHemoglobin -> {
                _state.update {
                    it.copy(
                        hemoglobin = event.hemoglobin
                    )
                }
            }
            is DisqualificationEvent.SetDetails -> {
                _state.update {
                    it.copy(
                        details = event.details
                    )
                }
            }
        }
    }
}