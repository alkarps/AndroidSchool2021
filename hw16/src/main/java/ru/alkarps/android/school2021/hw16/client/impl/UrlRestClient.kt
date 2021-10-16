package ru.alkarps.android.school2021.hw16.client.impl

import android.util.Log
import ru.alkarps.android.school2021.hw16.client.GET_URL
import ru.alkarps.android.school2021.hw16.client.POST_URL
import ru.alkarps.android.school2021.hw16.client.RestClient
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class UrlRestClient : RestClient {
    override fun doPost(): String = doCall(true)

    override fun doGet(): String = doCall(false)

    private fun doCall(post: Boolean): String {
        lateinit var connection: HttpsURLConnection
        return try {
            connection = getConnection(post)
            connection.connect()
            val responseCode = connection.responseCode
            if (responseCode != 200) "Response code: $responseCode" else {
                BufferedReader(InputStreamReader(connection.inputStream)).use {
                    it.lineSequence().joinToString("\n")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            e.toString()
        } finally {
            connection.disconnect()
        }
    }

    private fun getConnection(post: Boolean): HttpsURLConnection {
        val connection = URL(
            if (post) POST_URL else GET_URL
        ).openConnection()!! as HttpsURLConnection
        connection.connectTimeout = 3000
        connection.readTimeout = 3000
        connection.addRequestProperty("accept-encoding", "application/gzip")
        connection.addRequestProperty("x-rapidapi-host", "google-translate1.p.rapidapi.com")
        connection.addRequestProperty(
            "x-rapidapi-key",
            "a55d4279f6mshf44043b6e929001p1c527cjsn1d2ee610a1e6"
        )
        if (post) {
            connection.requestMethod = "POST"
            connection.doOutput = true
            BufferedWriter(OutputStreamWriter(connection.outputStream)).use {
                it.write("q=Hello%2C%20world!&target=ru&source=en")
                it.flush()
            }
        }
        return connection
    }

    companion object {
        private const val TAG = "UrlRestClient"
    }
}