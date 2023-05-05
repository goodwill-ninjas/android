package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.restClient.RestClient
import retrofit2.awaitResponse
import java.lang.Exception


class LoginViewModel(appObj: Application): AndroidViewModel(appObj) {
    private val _status = MutableStateFlow(Result(state = State.Loading, status = 0))
    val status get() = _status

    init {
        fetchStatus()
    }

    private fun fetchStatus() {
        viewModelScope.launch {
            try {
                val call = RestClient().getService().fetchHealthCheck()
                val response = call?.awaitResponse()
                if (response?.isSuccessful == true) {
                    val getResponse = response.body()
                    if (getResponse != null) {
                        _status.emit(Result(state = State.Success, status = getResponse.status))
                    }
                }
                else {
                    _status.emit(Result(state = State.Failed, status = 0))
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", e.message ?: "", e)
                _status.emit(Result(state = State.Failed, status = 0))
            }
        }
    }
}

data class Result(val state: State, val status: Int)

enum class State {
    Success,
    Failed,
    Loading
}