package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.userFeat

import com.fasterxml.jackson.annotation.JsonProperty

data class UserFeatResponse(
    @JsonProperty("userId") val userId: Int = 0,
    @JsonProperty("featId") val featId: String = "",
    @JsonProperty("featName") val featName: String = "",
    @JsonProperty("featDescription") val featDescription: String = "",
    @JsonProperty("achievedRanks") val achievedRanks: String = "",
    @JsonProperty("nextRanks") val nextRanks: String = ""
)