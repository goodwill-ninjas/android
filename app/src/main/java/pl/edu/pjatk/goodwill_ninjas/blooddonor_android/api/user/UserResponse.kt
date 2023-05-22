package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse (
    @JsonProperty("id") val id: Int,
    @JsonProperty("email") val email: String,
    @JsonProperty("username") val username: String,
    @JsonProperty("blood_type") val bloodType: String,
    @JsonProperty("gender") val gender: String,
    @JsonProperty("settings") val settings: Settings,
    @JsonProperty("avatar") val avatar: Int,
    @JsonProperty("has_verified_email") val hasVerifiedEmail: Boolean,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("expDetails") val expDetails: ExpDetails
        )

data class Settings (
    @JsonProperty("id") val id: Int,
    @JsonProperty("theme") val theme: String,
    @JsonProperty("font_size") val fontSize: String,
    @JsonProperty("event_notifications") val eventNotifications: Boolean,
    @JsonProperty("reminder_notifications") val reminderNotifications: Boolean,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("deleted_at") val deletedAt: String,
        )

data class ExpDetails (
    @JsonProperty("level") val level: Int,
    @JsonProperty("currentExperience") val currentExperience: Int,
    @JsonProperty("minExperience") val minExperience: Int,
    @JsonProperty("maxExperience") val maxExperience: Int
        )
