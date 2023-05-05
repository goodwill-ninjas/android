package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.auth.model

data class Auth(
    val email: String,
    val username: String,
    val password: String,
    val blood_type: String,
    val avatar_id: String,
    val gender: String,
)
