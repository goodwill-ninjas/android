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
    @JsonProperty("exp_details") val exp_details: ExpDetails? = ExpDetails(),
    @JsonProperty("can_donate_after") val can_donate_after: String? = ""
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
    @JsonProperty("current_experience") val current_experience: Int? = 0,
    @JsonProperty("min_experience") val min_experience: Int? = 0,
    @JsonProperty("max_experience") val max_experience: Int? = 0
        )
