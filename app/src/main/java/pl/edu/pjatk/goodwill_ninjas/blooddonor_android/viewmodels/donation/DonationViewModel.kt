package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation.DonationBody
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation.DonationService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel

class DonationViewModel(
    private val dao: DonationDao,
    context: Context
) : ViewModel() {
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

    private val _state = MutableStateFlow(DonationState())
    private val _donations = dao.getAll()
    val state = combine(_state, _donations) { state, donations ->
        state.copy(
            donations = donations
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DonationState())

    private val donations = MutableStateFlow(listOf(DonationBody()))

    private suspend fun addDonation(donation: DonationBody, token: String) {
        val service = DonationService()
        coroutineScope {
            service.successfulAddDonationResponse(donation, token)
        }
    }

    suspend fun getDonations(userId: Int, token: String) {
        val service = DonationService()
        coroutineScope {
            val donations = service.successfulDonationsResponse(userId, token)
            if (donations != null) {
                if (donations.size != dao.getAll().first().size) {

                }
            }
        }
    }

    fun onEvent(event: DonationEvent) {
        when (event) {
            is DonationEvent.SaveDonation -> {
                val companionUserId = state.value.companionUserId
                val donatedType = state.value.donatedType
                val amount = state.value.amount
                val bloodPressure = state.value.bloodPressure
                val hemoglobin = state.value.hemoglobin
                val details = state.value.details
                val createdAt = state.value.createdAt
                val deletedAt = state.value.deletedAt
                val hand = state.value.hand
                val bloodCenter = state.value.bloodCenter

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
                    deletedAt = deletedAt,
                    hand = hand,
                    bloodCenter = bloodCenter
                )
                viewModelScope.launch {
                    dao.upsertDonation(donation)
                    val donationBody = userId?.let {
                        DonationBody(
                            user_id = it,
                            disqualified = false,
                            companion_user_id = companionUserId,
                            donated_type = DonationParsers().parseDonationType(donatedType),
                            amount = amount,
                            blood_pressure = bloodPressure,
                            hemoglobin = hemoglobin,
                            arm = hand,
                            details = details,
                            donated_at = DonationParsers().parseToDate(createdAt)
                        )
                    }
                    if (donationBody != null) {
                        addDonation(donationBody, token)
                    }
                }
                _state.update {
                    it.copy(
                        donatedType = "",
                        companionUserId = null,
                        amount = 0,
                        bloodPressure = null,
                        hemoglobin = null,
                        details = null,
                        createdAt = null,
                        deletedAt = null,
                        hand = null,
                        bloodCenter = null
                    )
                }
            }

            is DonationEvent.SetCompanionUserId -> {
                _state.update {
                    it.copy(
                        companionUserId = event.companionUserId
                    )
                }
            }

            is DonationEvent.SetDonatedType -> {
                _state.update {
                    it.copy(
                        donatedType = event.donatedType
                    )
                }
            }

            is DonationEvent.SetAmount -> {
                _state.update {
                    it.copy(
                        amount = event.amount
                    )
                }
            }

            is DonationEvent.SetBloodPressure -> {
                _state.update {
                    it.copy(
                        bloodPressure = event.bloodPressure
                    )
                }
            }

            is DonationEvent.SetHemoglobin -> {
                _state.update {
                    it.copy(
                        hemoglobin = event.hemoglobin
                    )
                }
            }

            is DonationEvent.SetDetails -> {
                _state.update {
                    it.copy(
                        details = event.details
                    )
                }
            }

            is DonationEvent.SetCreatedAt -> {
                _state.update {
                    it.copy(
                        createdAt = event.createdAt
                    )
                }
            }

            is DonationEvent.SetDeletedAt -> {
                _state.update {
                    it.copy(
                        deletedAt = event.deletedAt
                    )
                }
            }

            is DonationEvent.SetHand -> {
                _state.update {
                    it.copy(
                        hand = event.hand
                    )
                }
            }

            is DonationEvent.SetBloodCenter -> {
                _state.update {
                    it.copy(
                        bloodCenter = event.bloodCenter
                    )
                }
            }
        }
    }

}