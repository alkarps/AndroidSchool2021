package ru.alkarps.android.school2021.hw16.client

import ru.alkarps.android.school2021.hw16.R
import ru.alkarps.android.school2021.hw16.client.impl.OkRestClient
import ru.alkarps.android.school2021.hw16.client.impl.UrlRestClient

interface RestClient {
    fun doPost(): String
    fun doGet(): String

    companion object {
        fun createClient(id: Int): RestClient =
            if (id == R.id.client_choice_url) UrlRestClient()
            else OkRestClient()
    }
}