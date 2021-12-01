package ru.alkarps.android.school2021.hw18.data.language.api.impl

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.alkarps.android.school2021.hw18.data.api.ApiConst
import ru.alkarps.android.school2021.hw18.data.language.api.LanguageApi
import ru.alkarps.android.school2021.hw18.data.language.model.LanguageDTO
import ru.alkarps.android.school2021.hw18.data.language.model.LanguagesResponseDTO
import ru.alkarps.android.school2021.hw18.domen.model.exception.HolidayApiException
import javax.inject.Inject

/**
 * Реализация [LanguageApi]
 *
 * @property okHttpClient [OkHttpClient] для вызова HolidayApi
 * @property jsonSerializer [Json] для десериализации ответа
 * @constructor Новый экземпляр реализации [LanguageApi]
 */
class ImplLanguageApi @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val jsonSerializer: Json
) : LanguageApi {
    override fun getLanguages(): List<LanguageDTO> {
        try {
            val request = Request.Builder()
                .url(buildUrl())
                .get()
                .build()
            val response = okHttpClient.newCall(request).execute()
            if (response.code == 200) {
                return response.body?.string()
                    ?.let { jsonSerializer.decodeFromString<LanguagesResponseDTO>(it) }
                    ?.languages ?: throw HolidayApiException()
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
        ApiConst.LANGUAGES_URL.toHttpUrl().newBuilder()
            .addQueryParameter("key", ApiConst.KEY)
            .build()
}