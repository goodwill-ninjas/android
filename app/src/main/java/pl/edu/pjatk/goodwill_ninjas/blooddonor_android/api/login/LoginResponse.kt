package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponse(
    @JsonProperty("token") val token: String
)
