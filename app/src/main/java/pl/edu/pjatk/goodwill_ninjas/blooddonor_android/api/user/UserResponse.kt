package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse (
    @JsonProperty("id") val id: Int = 0,
    @JsonProperty("email") val email: String? = "",
    @JsonProperty("username") val username: String? = "",
    @JsonProperty("password") val password: String? = "",
    @JsonProperty("blood_type") val bloodType: String? = "",
    @JsonProperty("gender") val gender: String? = "",
    @JsonProperty("settings") val settings: Settings? = Settings(),
    @JsonProperty("avatar_id") val avatarId: Int? = 0,
    @JsonProperty("has_verified_email") val hasVerifiedEmail: Boolean? = false,
    @JsonProperty("created_at") val createdAt: String? = "",
    @JsonProperty("expDetails") val expDetails: ExpDetails? = ExpDetails()
        )

data class Settings (
    @JsonProperty("id") val id: Int = 0,
    @JsonProperty("theme") val theme: String? = "",
    @JsonProperty("font_size") val fontSize: String? = "",
    @JsonProperty("event_notifications") val eventNotifications: Boolean? = false,
    @JsonProperty("reminder_notifications") val reminderNotifications: Boolean? = false,
    @JsonProperty("created_at") val createdAt: String? = "",
    @JsonProperty("updated_at") val updatedAt: String? = "",
        )

data class ExpDetails (
    @JsonProperty("level") val level: Int = 0,
    @JsonProperty("currentExperience") val currentExperience: Int? = 0,
    @JsonProperty("minExperience") val minExperience: Int? = 0,
    @JsonProperty("maxExperience") val maxExperience: Int? = 0
        )
