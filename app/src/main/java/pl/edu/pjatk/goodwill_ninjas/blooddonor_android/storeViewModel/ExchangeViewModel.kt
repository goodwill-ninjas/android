package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.storeViewModel

import android.content.Context
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ExchangeViewModel(private val context: Context) {
    private val store = ExchangeStore(context)

    fun getBloodCentre() = runBlocking { store.readDonationBloodCentre.first() }
    fun saveBloodCentre(bloodCentre: String) = runBlocking { store.storeDonationBloodCentre(bloodCentre) }

    fun getCreatedAt() = runBlocking { store.readCreatedAt.first() }
    fun saveCreatedAt(createdAt: Long) = runBlocking { store.storeCreatedAt(createdAt) }

    fun getAmount() = runBlocking { store.readAmount.first() }
    fun saveAmount(amount: Int) = runBlocking { store.storeAmount(amount) }

    fun getDonationType() = runBlocking { store.readDonationType.first() }
    fun saveDonationType(donationType: String) = runBlocking { store.storeDontationType(donationType) }



}