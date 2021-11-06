package ru.alkarps.android.school2021.hw18.data.country.api.impl

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.alkarps.android.school2021.hw18.data.api.ApiConst
import ru.alkarps.android.school2021.hw18.data.country.api.CountryApi
import ru.alkarps.android.school2021.hw18.data.country.model.CountriesResponseDTO
import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.domen.model.exception.HolidayApiException

/**
 * Реализация [CountryApi]
 *
 * @property okHttpClient [OkHttpClient] для вызова HolidayApi
 * @property jsonSerializer [Json] для десериализации ответа
 * @constructor Новый экземпляр реализации [CountryApi]
 */
class ImplCountryApi(
    private val okHttpClient: OkHttpClient,
    private val jsonSerializer: Json
) : CountryApi {
    override fun getCountries(): List<CountryDTO> {
        try {
            val request = Request.Builder()
                .url(buildUrl())
                .get()
                .build()
            val response = okHttpClient.newCall(request).execute()
            if (response.code == 200) {
                return response.body?.string()
                    ?.let { jsonSerializer.decodeFromString<CountriesResponseDTO>(it) }
                    ?.countries ?: throw HolidayApiException()
            } else {
                response.body?.close()
                throw HolidayApiException()
            }
        } catch (e: HolidayApiException) {
            throw e
        } catch (e: Exception) {
            throw HolidayApiException(e)
        }
    }

    private fun buildUrl() =
        ApiConst.HOLIDAYS_URL.toHttpUrl().newBuilder()
            .addQueryParameter("key", ApiConst.KEY)
            .build()
}