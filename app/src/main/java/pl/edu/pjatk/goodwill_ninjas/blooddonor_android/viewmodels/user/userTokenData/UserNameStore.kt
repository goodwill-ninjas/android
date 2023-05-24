package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserNameStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userName")
        private val USER_NAME = stringPreferencesKey("user_name")
    }

    val getUserName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[UserNameStore.USER_NAME] ?: ""
    }

    suspend fun saveUserName(userName: String) {
        context.dataStore.edit { preferences ->
            preferences[UserNameStore.USER_NAME] = userName
        }
    }
}