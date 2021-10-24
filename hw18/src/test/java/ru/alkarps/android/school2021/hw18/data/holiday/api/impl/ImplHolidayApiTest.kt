package ru.alkarps.android.school2021.hw18.data.holiday.api.impl

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okio.IOException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.api.ApiConst
import ru.alkarps.android.school2021.hw18.data.api.model.RequestsDTO
import ru.alkarps.android.school2021.hw18.data.holiday.api.HolidayApi
import ru.alkarps.android.school2021.hw18.data.holiday.model.DateInfoDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.HolidayResponseDTO
import ru.alkarps.android.school2021.hw18.data.holiday.model.WeekdayDTO
import ru.alkarps.android.school2021.hw18.domen.model.HolidayApiException
import ru.alkarps.android.school2021.hw18.domen.model.Period

class ImplHolidayApiTest {
    private val language = "RU"
    private val country = "USA"
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var call: Call
    private lateinit var response: Response
    private lateinit var body: ResponseBody
    private lateinit var jsonSerializer: Json
    private lateinit var testApi: HolidayApi

    @Before
    fun setUp() {
        call = mockk()
        response = mockk()
        body = mockk()
        okHttpClient = mockk()
        every { okHttpClient.newCall(any()) } returns call
        jsonSerializer = mockk()
        testApi = ImplHolidayApi(okHttpClient, jsonSerializer)
    }

    @Test
    fun getHolidays_whenStatusIs200_thenReturnsHolidays() {
        val bodyString = "bodyString"
        every { call.execute() } returns response
        every { response.code } returns 200
        every { response.body } returns body
        every { body.string() } returns bodyString
        val holidays = listOf(
            HolidayDTO(
                "", "", "", true, "", "",
                WeekdayDTO(DateInfoDTO("", ""), DateInfoDTO("", ""))
            )
        )
        val holidayResponse = HolidayResponseDTO(200, RequestsDTO(1, 0, ""), holidays)
        every { jsonSerializer.decodeFromString<HolidayResponseDTO>(any()) } returns holidayResponse
        assertThat(testApi.getHolidays(Period(2020), country, language)).isEqualTo(holidays)
        verify {
            okHttpClient.newCall(withArg {
                assertThat(it).isNotNull
                    .hasFieldOrPropertyWithValue("url", buildExpectedUrl())
            })
        }
        verify { call.execute() }
        verify { response.code }
        verify { response.body }
        verify { body.string() }
        verify { jsonSerializer.decodeFromString<HolidayResponseDTO>(bodyString) }
    }

    @Test
    fun getHolidays_whenStatusIs200ButBodyIsNull_thenThrowHolidayApiException() {
        every { call.execute() } returns response
        every { response.code } returns 200
        every { response.body } returns null
        assertThatCode { testApi.getHolidays(Period(2020, 11), country, language) }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify {
            okHttpClient.newCall(withArg {
                assertThat(it).isNotNull
                    .hasFieldOrPropertyWithValue("url", buildExpectedUrl(month = true))
            })
        }
        verify { call.execute() }
        verify { response.code }
        verify { response.body }
        verify(exactly = 0) { body.string() }
        verify(exactly = 0) { jsonSerializer.decodeFromString<HolidayResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenStatusIs200AndHolidaysIsNull_thenThrowHolidayApiException() {
        val bodyString = "bodyString"
        every { call.execute() } returns response
        every { response.code } returns 200
        every { response.body } returns body
        every { body.string() } returns bodyString
        val holidayResponse = HolidayResponseDTO(200, RequestsDTO(1, 0, ""), null)
        every { jsonSerializer.decodeFromString<HolidayResponseDTO>(any()) } returns holidayResponse
        assertThatCode { testApi.getHolidays(Period(2020, null, 22), country, language) }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify {
            okHttpClient.newCall(withArg {
                assertThat(it).isNotNull
                    .hasFieldOrPropertyWithValue("url", buildExpectedUrl(day = true))
            })
        }
        verify { call.execute() }
        verify { response.code }
        verify { response.body }
        verify { body.string() }
        verify { jsonSerializer.decodeFromString<HolidayResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenStatusIsNot200AndBodyExist_thenCloseBodyAndThrowHolidayApiException() {
        every { call.execute() } returns response
        every { response.code } returns 401
        every { response.body } returns body
        justRun { body.close() }
        assertThatCode { testApi.getHolidays(Period(2020, 11, 22), country, language) }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify {
            okHttpClient.newCall(withArg {
                assertThat(it).isNotNull
                    .hasFieldOrPropertyWithValue("url", buildExpectedUrl(month = true, day = true))
            })
        }
        verify { call.execute() }
        verify { response.code }
        verify { response.body }
        verify { body.close() }
        verify(exactly = 0) { jsonSerializer.decodeFromString<HolidayResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenStatusIsNot200AndBodyNotExist_thenNotCloseBodyAndThrowHolidayApiException() {
        every { call.execute() } returns response
        every { response.code } returns 429
        every { response.body } returns null
        assertThatCode { testApi.getHolidays(Period(2020), country, language) }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify {
            okHttpClient.newCall(withArg {
                assertThat(it).isNotNull
                    .hasFieldOrPropertyWithValue("url", buildExpectedUrl())
            })
        }
        verify { call.execute() }
        verify { response.code }
        verify { response.body }
        verify(exactly = 0) { body.close() }
        verify(exactly = 0) { jsonSerializer.decodeFromString<HolidayResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenOkThrowExcaption_thenThrowHolidayApiException() {
        val ioException = IOException("")
        every { call.execute() } throws ioException
        assertThatCode { testApi.getHolidays(Period(2020), country, language) }
            .isInstanceOf(HolidayApiException::class.java)
            .hasCause(ioException)
        verify {
            okHttpClient.newCall(withArg {
                assertThat(it).isNotNull
                    .hasFieldOrPropertyWithValue("url", buildExpectedUrl())
            })
        }
        verify { call.execute() }
    }

    private fun buildExpectedUrl(month: Boolean = false, day: Boolean = false) =
        ApiConst.HOLIDAYS_URL.toHttpUrl().newBuilder()
            .addQueryParameter("key", ApiConst.KEY)
            .addQueryParameter("country", country)
            .addQueryParameter("language", language)
            .addQueryParameter("year", "2020")
            .apply {
                if (month) addQueryParameter("month", "11")
                if (month && day) addQueryParameter("day", "22")
            }.build()
}