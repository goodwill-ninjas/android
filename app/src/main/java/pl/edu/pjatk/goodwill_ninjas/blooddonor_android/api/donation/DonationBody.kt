package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation

import com.fasterxml.jackson.annotation.JsonProperty

data class DonationBody(
    @JsonProperty("user_id") val user_id: Int = 0,
    @JsonProperty("disqualified") val disqualified: Boolean? = false,
    @JsonProperty("companion_user_id") val companion_user_id: Int? = null,
    @JsonProperty("donated_type") val donated_type: String? = null,
    @JsonProperty("amount") val amount: Int? = 450,
    @JsonProperty("blood_pressure") val blood_pressure: String? = null,
    @JsonProperty("hemoglobin") val hemoglobin: String? = null,
    @JsonProperty("arm") val arm: String? = null,
    @JsonProperty("details") val details: String? = null,
    @JsonProperty("donated_at") val donated_at: String? = null
)