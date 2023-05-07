package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import java.lang.Long

class DonationViewModel(
    private val dao: DonationDao
): ViewModel() {

    var lastDonationDate by mutableStateOf("")
    fun updateLastDonationDate(dateInput: String){
        lastDonationDate = dateInput
    }


    private val _state = MutableStateFlow(DonationState())
    private val _donations = dao.getAll()
    val state = combine( _state, _donations ) { state, donations ->
        state.copy(
            donations = donations
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DonationState())

    fun onEvent(event: DonationEvent) {
        when (event) {
            is DonationEvent.SaveDonation -> {
                val companionUserId = state.value.companionUserId
                val donatedType  = state.value.donatedType
                val amount = state.value.amount
                val bloodPressure = state.value.bloodPressure
                val hemoglobin = state.value.hemoglobin
                val details = state.value.details
                val createdAt = state.value.createdAt
                val deletedAt = state.value.deletedAt

                if (donatedType.isBlank() || createdAt == null || amount == 0) {
                    return
                }
                val donation = Donation(
                    donatedType = donatedType,
                    amount = amount,
                    details = details,
                    bloodPressure = bloodPressure,
                    companionUserId = companionUserId,
                    hemoglobin = hemoglobin,
                    createdAt = createdAt,
                    deletedAt = deletedAt
                )
                viewModelScope.launch {
                    dao.upsertDonation(donation)
                }
                _state.update { it.copy(
                    donatedType = "",
                    companionUserId = null,
                    amount = 0,
                    bloodPressure = null,
                    hemoglobin = null,
                    details = null,
                    createdAt = null,
                    deletedAt = null
                ) }
            }
            is DonationEvent.SetCompanionUserId -> {
                _state.update { it.copy(
                    companionUserId = event.companionUserId
                ) }
            }
            is DonationEvent.SetDonatedType -> {
                _state.update { it.copy(
                    donatedType = event.donatedType
                ) }
            }
            is DonationEvent.SetAmount -> {
                _state.update { it.copy(
                    amount = event.amount
                ) }
            }
            is DonationEvent.SetBloodPressure -> {
                _state.update { it.copy(
                    bloodPressure = event.bloodPressure
                ) }
            }
            is DonationEvent.SetHemoglobin -> {
                _state.update { it.copy(
                    hemoglobin = event.hemoglobin
                ) }
            }
            is DonationEvent.SetDetails -> {
                _state.update { it.copy(
                    details = event.details
                ) }
            }
            is DonationEvent.SetCreatedAt -> {
                _state.update { it.copy(
                    createdAt = event.createdAt
                ) }
            }
            is DonationEvent.SetDeletedAt -> {
                _state.update { it.copy(
                    deletedAt = event.deletedAt
                ) }
            }
        }
    }
}