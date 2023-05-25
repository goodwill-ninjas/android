package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.bloodCenterDetails

import com.fasterxml.jackson.annotation.JsonProperty

data class BloodCenterDetailsResponse(
    @JsonProperty("id") val id: Int? = 0,
    @JsonProperty("name") val name: String? = "",
    @JsonProperty("blood_center_details") val bloodCenterDetails: List<BloodCapacity> = listOf(BloodCapacity()),
    @JsonProperty("street_name") val streetName: String? = "",
    @JsonProperty("street_number") val streetNumber: String? = "",
    @JsonProperty("postal_code") val postalCode: String? = "",
    @JsonProperty("city") val city: String? = "",
    @JsonProperty("voivodeship") val voivodeship: String? = "",
    @JsonProperty("geo_coordinates") val geoCoordinates: String? = "",
    @JsonProperty("phone_number") val phoneNumber: String? = "",
    @JsonProperty("open_from") val openFrom: String? = "",
    @JsonProperty("open_to") val openTo: String? = "",
    @JsonProperty("created_at") val createdAt: String? = ""
)

data class BloodCapacity (
    @JsonProperty("id") val id: Int? = 0,
    @JsonProperty("blood_type") val bloodType: String? = "",
    @JsonProperty("capacity") val capacity: String? = "",
    @JsonProperty("source_datetime") val sourceDatetime: String = "",
    @JsonProperty("updated_at") val updatedAt: String = "",
    @JsonProperty("created_at") val createdAt: String = ""
        )