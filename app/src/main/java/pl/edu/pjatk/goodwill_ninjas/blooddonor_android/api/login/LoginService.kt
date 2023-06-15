package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.login

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.ResponseBody
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient.RestClient

class LoginService {
    private val restClient = RestClient.getClient()
    private val api = restClient.create(LoginApi::class.java)

    suspend fun successfulLoginResponse(email: String, password: String): String {
        val loginBody = LoginBody(email, password)
        val loginResponse = api.login(loginBody)

        val successful = loginResponse?.isSuccessful
        val httpStatusCode = loginResponse?.code()
        val httpStatusMessage = loginResponse?.message()

        val body: LoginResponse? = loginResponse?.body()

        if (body != null) {
            return body.token
        }
        return ""
    }
    suspend fun errorLoginResponse(email: String, password: String) {
        val loginBody = LoginBody(email, password)
        val loginResponse = api.login(loginBody)

        val errorBody: ResponseBody? = loginResponse?.errorBody()

        val mapper = ObjectMapper()

        val mappedBody: LoginErrorResponse? = errorBody.let { notNullErrorBody ->
            mapper.readValue(notNullErrorBody.toString(), LoginErrorResponse::class.java)
        }
        Log.d("response", mappedBody.toString())
    }

    suspend fun successfulRegisterResponse(
        body: RegisterBody
    ): RegisterResponse? {
        val registerResponse = api.register(body)
        val httpStatusCode: Int? = registerResponse?.code()
        return registerResponse?.body()
    }
}