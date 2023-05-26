package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils

import android.util.Base64
import android.util.Log

class JWTUtils {
    fun decoded(JWTEncoded: String): String {
        return try {
            val split: List<String> = JWTEncoded.split(".")
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            getJson(split[1]);
        } catch (e: Exception) {
            Log.e("JWTUtils", "Exception")
            "Nie jesteś zalogowany!"
        }
    }
    private fun getJson(encoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(encoded, Base64.URL_SAFE)
        return String(decodedBytes)
    }
}