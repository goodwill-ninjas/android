package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.donation

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class DonationService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(DonationApi::class.java)

    suspend fun successfulDonationsResponse(id: Int, token: String): List<DonationResponse>? {
        val donationResponse = api.userDonations("Bearer $token", id)
        val successful = donationResponse.isSuccessful
        val httpStatusCode = donationResponse.code()
        val httpStatusMessage = donationResponse.message()

        return donationResponse.body()
    }

    suspend fun successfulAddDonationResponse(donationBody: DonationBody, token: String): Int? {
        val addDonationResponse = api.addDonation("Bearer $token", donationBody)
        val successful = addDonationResponse?.isSuccessful
        if (addDonationResponse != null) {
            return addDonationResponse.code()
        }
        return null
    }
}