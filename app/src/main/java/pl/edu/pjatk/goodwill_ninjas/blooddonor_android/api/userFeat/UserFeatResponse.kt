package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.userFeat

import com.fasterxml.jackson.annotation.JsonProperty

data class UserFeatResponse(
    @JsonProperty("userId") val userId: Int = 0,
    @JsonProperty("featId") val featId: String = "",
    @JsonProperty("featName") val featName: String = "",
    @JsonProperty("featDescription") val featDescription: String? = "",
    @JsonProperty("achievedRanks") val achievedRanks: List<UserFeatRanks> = listOf(UserFeatRanks()),
    @JsonProperty("nextRanks") val nextRanks: List<UserFeatRanks> = listOf(UserFeatRanks())
)

data class UserFeatRanks(
    @JsonProperty("id") val id: Int = 0,
    @JsonProperty("rank") val rank: Int = 0,
    @JsonProperty("requirement") val requirement: Int = 0,
    @JsonProperty("experience_award") val experienceAward: Int = 0,
    @JsonProperty("title_award") val titleAward: String? = null,
    @JsonProperty("created_at") val createdAt: String = ""
)