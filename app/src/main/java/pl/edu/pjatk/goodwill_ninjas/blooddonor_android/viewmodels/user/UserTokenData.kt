package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.user

import kotlinx.serialization.Serializable

@Serializable
data class UserTokenData(
    val context: UserContext
)

@Serializable
data class UserContext(
    val user: UserData
)

@Serializable
data class UserData (
    val userId: Int,
    val displayName: String
        )