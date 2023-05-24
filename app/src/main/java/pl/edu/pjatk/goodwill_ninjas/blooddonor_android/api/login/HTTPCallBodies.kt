package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginBody(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String
)

data class RegisterBody(
    @JsonProperty("email") val email: String = "",
    @JsonProperty("password") val password: String = "",
    @JsonProperty("username") val username: String = "",
    @JsonProperty("blood_type") val blood_type: String = "0 Rh+",
    @JsonProperty("gender") val gender: String = "Male",
    @JsonProperty("avatar_id") val avatarId: Int? = null
)