package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login

import android.util.Log
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class LoginService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(LoginApi::class.java)
    private val loginBody = LoginBody(email = "test@example.com", password = "Testing123")

    suspend fun successfulLoginResponse(): String {
        val loginResponse = api.login(loginBody)

        val successful = loginResponse?.isSuccessful
        val httpStatusCode = loginResponse?.code()
        val httpStatusMessage = loginResponse?.message()

        val body: LoginResponse? = loginResponse?.body()
//
//        val errorBody: ResponseBody? = loginResponse?.errorBody()
//        val mapper = ObjectMapper()
//
//        val mappedBody: LoginErrorResponse? = errorBody.let { notNullErrorBody ->
//            mapper.readValue(notNullErrorBody.toString(), LoginErrorResponse::class.java)
//        }
        Log.wtf("response", body.toString())
        if (body != null) {
            return body.token
        }
        return ""
    }
}