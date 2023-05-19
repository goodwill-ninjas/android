package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient

import okhttp3.Interceptor
import okhttp3.Response
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel

object RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("Outgoing request to ${request.url()}")
        return chain.proceed(request)
    }
}

