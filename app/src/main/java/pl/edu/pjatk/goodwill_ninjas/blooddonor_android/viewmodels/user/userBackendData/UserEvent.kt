package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.userBackendData

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user.UserResponse

sealed interface UserEvent {
    data class SetUser(val user: UserResponse): UserEvent
}