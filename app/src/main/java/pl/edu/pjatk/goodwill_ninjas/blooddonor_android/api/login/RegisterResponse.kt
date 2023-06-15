package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterResponse(
    @JsonProperty("email") val email: String?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("blood_type") val blood_type: String?,
    @JsonProperty("gender") val gender: String?,
    @JsonProperty("experience") val experience: String?,
    @JsonProperty("has_verified_email") val has_verified_email: Boolean?,
    @JsonProperty("settings") val settings: Settings?,
    @JsonProperty("avatar") val avatar: Int?,
    @JsonProperty("avatar_id") val avatar_id: Int?,
    @JsonProperty("id") val id: Int?,
    @JsonProperty("created_at") val created_at: String?
)

data class Settings(
    @JsonProperty("id") val id: Int?,
    @JsonProperty("theme") val theme: String?,
    @JsonProperty("font_size") val font_size: String?,
    @JsonProperty("event_notifications") val event_notifications: Boolean?,
    @JsonProperty("reminder_notifications") val reminder_notifications: Boolean?,
    @JsonProperty("created_at") val created_at: String?,
    @JsonProperty("updated_at") val updated_at: String?
)