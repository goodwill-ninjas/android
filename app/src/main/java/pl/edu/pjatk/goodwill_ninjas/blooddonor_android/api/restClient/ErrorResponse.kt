package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse (
    @JsonProperty("message") val message: String
    )