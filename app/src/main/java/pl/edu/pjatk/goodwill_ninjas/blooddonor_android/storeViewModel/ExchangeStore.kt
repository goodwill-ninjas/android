package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.storeViewModel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ExchangeStore(var context: Context) {
    companion object {
        val CREATED_AT_KEY = longPreferencesKey("CREATED_AT")
        val DONATION_TYPE_KEY = stringPreferencesKey("DONATON_TYPE")
        val AMOUNT_KEY = intPreferencesKey("AMOUNT")
        val BLOOD_CENTRE_KEY = stringPreferencesKey("DONATION_CENTRE")
    }
    suspend fun storeDonationBloodCentre(
        bloodCentre: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[BLOOD_CENTRE_KEY] = bloodCentre
        }
    }
    val readDonationBloodCentre: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[BLOOD_CENTRE_KEY] ?: ""
    }
    suspend fun storeCreatedAt(
        createdAt:Long
    ) {
        context.dataStore.edit { preferences ->
            preferences[CREATED_AT_KEY] = createdAt
        }
    }
    val readCreatedAt: Flow<Long> = context.dataStore.data.map { preferences ->

        preferences[CREATED_AT_KEY] ?: 0L
    }
    suspend fun storeDontationType(
        donationType:String
    ) {
        context.dataStore.edit { preferences ->
            preferences[DONATION_TYPE_KEY] = donationType
        }
    }
    val readDonationType: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[DONATION_TYPE_KEY] ?: ""
    }
    suspend fun storeAmount(
        amount: Int
    ) {
        context.dataStore.edit { preferences ->
            preferences[AMOUNT_KEY] = amount
        }
    }
    val readAmount: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[AMOUNT_KEY] ?: 0
    }
}