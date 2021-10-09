package ru.alkarps.android.school2021.hw16.client.impl

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import ru.alkarps.android.school2021.hw16.client.GET_URL
import ru.alkarps.android.school2021.hw16.client.POST_URL
import ru.alkarps.android.school2021.hw16.client.RestClient

class OkRestClient : RestClient {
    private val client = OkHttpClient()

    override fun doPost(): String = doCall(true)

    override fun doGet(): String = doCall(false)

    private fun doCall(post: Boolean): String {
        val request = Request.Builder()
            .url(if (post) POST_URL else GET_URL)
            .addHeader("accept-encoding", "application/gzip")
            .addHeader("x-rapidapi-host", "google-translate1.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "a55d4279f6mshf44043b6e929001p1c527cjsn1d2ee610a1e6")
            .apply {
                if (post) {
                    val body = "q=Hello%2C%20world!&target=ru&source=en".toRequestBody(null)
                    post(body)
                    addHeader("content-type", "application/x-www-form-urlencoded")
                } else {
                    get()
                }
            }
            .build()

        Log.w(TAG, request.toString())
        val response = client.newCall(request).execute()
        Log.w(TAG, response.toString())
        return if (response.code != 200) "Response code: ${response.code}"
        else response.body?.string() ?: ""
    }

    companion object {
        private const val TAG = "OkRestClient"
    }
}