package ru.alkarps.android.school2021.hw18.data.holiday.api.impl

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.alkarps.android.school2021.hw18.data.api.ApiConst
import ru.alkarps.android.school2021.hw18.data.holiday.api.HolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayResponseDTO
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.domen.model.exception.HolidayApiException
import javax.inject.Inject

/**
 * Реализация [HolidayApi]
 *
 * Бесплатная версия ограничена прошлым годом, поэтому при получении 402 кода - возвращаем пустойсписок.
 *
 * @property okHttpClient [OkHttpClient] для вызова HolidayApi
 * @property jsonSerializer [Json] для десериализации ответа
 * @constructor Новый экземпляр реализации [HolidayApi]
 */
class ImplHolidayApi @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val jsonSerializer: Json
) : HolidayApi {
    override fun getHolidays(period: Period, country: String, language: String): List<HolidayDTO> {
        try {
            val request = Request.Builder()
                .url(buildUrl(period, country, language))
                .get()
                .build()
            val response = okHttpClient.newCall(request).execute()
            if (response.code == 200) {
                return response.body?.string()
                    ?.let { jsonSerializer.decodeFromString<HolidayResponseDTO>(it) }
                    ?.holidays ?: throw HolidayApiException()
            } else {
                response.body?.close()
                if (response.code == 402) return emptyList()
                throw HolidayApiException()
            }
        } catch (e: HolidayApiException) {
            throw e
        } catch (e: Exception) {
            throw HolidayApiException(e)
        }
    }

    private fun buildUrl(period: Period, country: String, language: String) =
        ApiConst.HOLIDAYS_URL.toHttpUrl().newBuilder()
            .addQueryParameter("key", ApiConst.KEY)
            .addQueryParameter("country", country)
            .addQueryParameter("language", language)
            .addQueryParameter("year", period.year.toString().padStart(4, '0'))
            .apply {
                period.month?.also { month ->
                    addQueryParameter("month", month.toString())
                    period.day?.also { addQueryParameter("day", it.toString()) }
                }
            }.build()
}