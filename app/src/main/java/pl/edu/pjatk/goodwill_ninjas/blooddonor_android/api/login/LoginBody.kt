package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginBody(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String
)
