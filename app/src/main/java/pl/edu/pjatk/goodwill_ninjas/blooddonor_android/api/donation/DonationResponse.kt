package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation

import com.fasterxml.jackson.annotation.JsonProperty

data class DonationResponse(
    @JsonProperty("id") val id: Int = 0,
    @JsonProperty("user_id") val userId: Int = 0,
    @JsonProperty("disqualified") val disqualified: Boolean? = false,
    @JsonProperty("companion_user_id") val companionUserId: Int? = null,
    @JsonProperty("donated_type") val donatedType: String? = null,
    @JsonProperty("amount") val amount: Int? = 450,
    @JsonProperty("blood_pressure") val bloodPressure: String? = null,
    @JsonProperty("hemoglobin") val hemoglobin: Double? = null,
    @JsonProperty("arm") val arm: String? = null,
    @JsonProperty("details") val details: String? = null,
    @JsonProperty("donated_at") val donatedAt: String? = null,
    @JsonProperty("created_at") val createdAt: String? = null
)