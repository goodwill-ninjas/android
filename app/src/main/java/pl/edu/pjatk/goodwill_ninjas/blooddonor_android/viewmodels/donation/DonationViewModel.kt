package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

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
                val donatedType  = state.value.donated_type
                val donationDate = state.value.donation_date
                val amount = state.value.amount
                if (donatedType.isBlank() || donationDate.equals(0) || amount == 0) {
                    return
                }
                val donation = Donation(
                    donatedType = donatedType,
                    donationDate = donationDate,
                    amount = amount
                )
                viewModelScope.launch {
                    dao.upsertDonation(donation)
                }
                _state.update { it.copy(
                    donated_type = "",
                    donation_date = 0,
                    amount = 0
                ) }
            }
            is DonationEvent.SetDonatedType -> {
                _state.update { it.copy(
                    donated_type = event.donatedType
                ) }
            }
            is DonationEvent.SetAmount -> {
                _state.update { it.copy(
                    amount = event.amount
                ) }
            }
            is DonationEvent.SetDonationDate -> {
                _state.update { it.copy(
                    donation_date = event.donationDate
                ) }
            }
        }
    }
}