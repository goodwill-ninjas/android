package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user.userBackendData

data class UserState (
    val id: Int = 0,
    val email: String = "",
    val username: String = "",
    val bloodType: String = "",
    val gender: String = "",
    val settings: Settings = Settings(),
    val avatar: Int = 0,
    val hasVerifiedEmail: Boolean = false,
    val createdAt: String = "",
    val expDetails: ExpDetails = ExpDetails()
)

data class Settings (
    val id: Int = 0,
    val theme: String = "",
    val fontSize: String = "",
    val eventNotifications: Boolean = false,
    val reminderNotifications: Boolean = false,
    val createdAt: String = "",
    val deletedAt: String = "",
)

data class ExpDetails (
    val level: Int = 0,
    val currentExperience: Int = 0,
    val minExperience: Int = 0,
    val maxExperience: Int = 0
)
