package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.runBlocking
import org.joda.time.Instant
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation.DonationBody
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation.DonationService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.Disqualification
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationDAO
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationEvent
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.UserViewModel

class DonationViewModel(
    private val dao: DonationDao,
    private val disqualificationDao: DisqualificationDAO,
    context: Context,
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

    private suspend fun addDonation(donation: DonationBody, token: String) {
        val service = DonationService()
        coroutineScope {
            service.successfulAddDonationResponse(donation, token)
        }
    }

    fun getDonations(userId: Int, token: String) = runBlocking {
        val service = DonationService()
        coroutineScope {
            val donations = service.successfulDonationsResponse(userId, token)
            if (donations != null) {
                if(
                    donations.filter { item -> item.disqualified == true }.size != disqualificationDao.getAll().first().size
                    || donations.filter { item -> item.disqualified == false }.size != dao.getAll().first().size
                ) {
                    dao.deleteAll()
                    disqualificationDao.deleteAll()
                    for (item in donations) {
                        if (item.disqualified == true) {
                            val disqualification = Disqualification(
                                companionUserId = item.companionUserId,
                                bloodPressure = item.bloodPressure,
                                dateStart = Instant.parse(item.createdAt).millis,
                                days = item.disqualificationDays,
                                details = item.details,
                                hemoglobin = item.hemoglobin.toString()
                            )
                            runBlocking {
                                disqualificationDao.upsertDisqualification(disqualification)
                            }
                        } else {
                            val donation = item.amount?.let {
                                Donation(
                                    companionUserId = item.companionUserId,
                                    donatedType = DonationParsers().parseDonationType(item.donatedType.toString()),
                                    amount = it,
                                    bloodPressure = item.bloodPressure,
                                    hemoglobin = item.hemoglobin,
                                    details = item.details,
                                    createdAt = Instant.parse(item.donatedAt).millis,
                                    deletedAt = null,
                                    hand = item.arm,
                                    bloodCenter = null
                                )
                            }
                            if (donation != null) {
                                runBlocking {
                                    dao.upsertDonation(donation)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: DonationEvent) {
        when (event) {
            is DonationEvent.SaveDonation -> {
                val companionUserId = _state.value.companionUserId
                val donatedType = _state.value.donatedType
                val amount = _state.value.amount
                val bloodPressure = _state.value.bloodPressure
                val hemoglobin = _state.value.hemoglobin
                val details = _state.value.details
                val createdAt = _state.value.createdAt
                val deletedAt = _state.value.deletedAt
                val hand = _state.value.hand
                val bloodCenter = _state.value.bloodCenter

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
                            hemoglobin = DonationParsers().parseHemoglobin(hemoglobin),
                            arm = DonationParsers().parseHand(hand),
                            details = details,
                            donated_at = DonationParsers().parseToDate(createdAt)
                        )
                    }
                    Log.d("donbody", donationBody.toString())
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