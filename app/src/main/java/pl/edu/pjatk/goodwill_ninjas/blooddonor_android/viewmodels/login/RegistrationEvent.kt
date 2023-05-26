package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login

sealed interface RegistrationEvent {
    data class SetEmail(val email: String): RegistrationEvent
    data class SetPassword(val password: String): RegistrationEvent
    data class SetUsername(val username: String): RegistrationEvent
    data class SetBloodType(val blood_type: String): RegistrationEvent
    data class SetGender(val gender: String): RegistrationEvent
}