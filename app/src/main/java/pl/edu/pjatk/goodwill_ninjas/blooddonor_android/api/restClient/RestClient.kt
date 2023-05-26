package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.restClient

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    private const val baseUrl = "https://api.pre.gwn.adamjurkiewicz.com/api/"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(RequestInterceptor)
        .build()

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
}