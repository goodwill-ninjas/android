package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.discqualification

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation.DonationBody
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation.DonationService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.Disqualification
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationDAO
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationParsers
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel

class DisqualificationViewModel(private val dao: DisqualificationDAO, context: Context) : ViewModel() {
    private val loginViewModel = LoginViewModel(context)
    private val token = loginViewModel.getToken()

    private lateinit var userViewModel: UserViewModel
    var userId: Int? = null

    init {
        if (token.isNotEmpty()) {
            userViewModel = UserViewModel(context, token)
            userId = userViewModel.getUserId()
        }
    }

    private val _state = MutableStateFlow(DisqualificationState())
    private val _disqualifications = dao.getAll()
    val state = combine(_state, _disqualifications) { state, disqualifications ->
        state.copy(
            disqualifications = disqualifications
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DisqualificationState())

    private suspend fun addDonation(donation: DonationBody, token: String) {
        val service = DonationService()
        coroutineScope {
            service.successfulAddDonationResponse(donation, token)
        }
    }


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
                    val donationBody = userId?.let {
                        DonationBody(
                            user_id = it,
                            disqualified = true,
                            companion_user_id = companionUserId,
                            blood_pressure = bloodPressure,
                            hemoglobin = hemoglobin,
                            details = details,
                            donated_at = DonationParsers().parseToDate(dateStart),
                            disqualification_days = days
                        )
                    }
                    if (donationBody != null) {
                        addDonation(donationBody, token)
                    }
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