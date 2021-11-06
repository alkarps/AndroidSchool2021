package ru.alkarps.android.school2021.hw18.data.country.api.impl

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
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.api.ApiConst
import ru.alkarps.android.school2021.hw18.data.api.model.RequestsDTO
import ru.alkarps.android.school2021.hw18.data.country.api.CountryApi
import ru.alkarps.android.school2021.hw18.data.country.model.CountriesResponseDTO
import ru.alkarps.android.school2021.hw18.data.country.model.CountryDTO
import ru.alkarps.android.school2021.hw18.domen.model.exception.HolidayApiException
import java.io.IOException

class ImplCountryApiTest {
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var call: Call
    private lateinit var response: Response
    private lateinit var body: ResponseBody
    private lateinit var jsonSerializer: Json
    private lateinit var testApi: CountryApi

    @Before
    fun setUp() {
        call = mockk()
        response = mockk()
        body = mockk()
        okHttpClient = mockk()
        every { okHttpClient.newCall(any()) } returns call
        jsonSerializer = mockk()
        testApi = ImplCountryApi(okHttpClient, jsonSerializer)
    }

    @After
    fun tearDown() {
        verify {
            okHttpClient.newCall(withArg {
                Assertions.assertThat(it).isNotNull
                    .hasFieldOrPropertyWithValue("url", buildExpectedUrl())
            })
        }
        verify { call.execute() }
    }

    @Test
    fun getHolidays_whenStatusIs200_thenReturnsHolidays() {
        val bodyString = "bodyString"
        every { call.execute() } returns response
        every { response.code } returns 200
        every { response.body } returns body
        every { body.string() } returns bodyString
        val countries = listOf(CountryDTO("", "", emptyMap(), emptyList(), "", emptyList()))
        val countryResponse = CountriesResponseDTO(200, RequestsDTO(1, 0, ""), countries)
        every { jsonSerializer.decodeFromString<CountriesResponseDTO>(any()) } returns countryResponse
        Assertions.assertThat(testApi.getCountries()).isEqualTo(countries)
        verify { response.code }
        verify { response.body }
        verify { body.string() }
        verify { jsonSerializer.decodeFromString<CountriesResponseDTO>(bodyString) }
    }

    @Test
    fun getHolidays_whenStatusIs200ButBodyIsNull_thenThrowHolidayApiException() {
        every { call.execute() } returns response
        every { response.code } returns 200
        every { response.body } returns null
        Assertions.assertThatCode { testApi.getCountries() }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify { response.code }
        verify { response.body }
        verify(exactly = 0) { body.string() }
        verify(exactly = 0) { jsonSerializer.decodeFromString<CountriesResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenStatusIs200AndHolidaysIsNull_thenThrowHolidayApiException() {
        val bodyString = "bodyString"
        every { call.execute() } returns response
        every { response.code } returns 200
        every { response.body } returns body
        every { body.string() } returns bodyString
        val countryResponse = CountriesResponseDTO(200, RequestsDTO(1, 0, ""), null)
        every { jsonSerializer.decodeFromString<CountriesResponseDTO>(any()) } returns countryResponse
        Assertions.assertThatCode { testApi.getCountries() }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify { response.code }
        verify { response.body }
        verify { body.string() }
        verify { jsonSerializer.decodeFromString<CountriesResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenStatusIsNot200AndBodyExist_thenCloseBodyAndThrowHolidayApiException() {
        every { call.execute() } returns response
        every { response.code } returns 401
        every { response.body } returns body
        justRun { body.close() }
        Assertions.assertThatCode { testApi.getCountries() }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify { response.code }
        verify { response.body }
        verify { body.close() }
        verify(exactly = 0) { jsonSerializer.decodeFromString<CountriesResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenStatusIsNot200AndBodyNotExist_thenNotCloseBodyAndThrowHolidayApiException() {
        every { call.execute() } returns response
        every { response.code } returns 429
        every { response.body } returns null
        Assertions.assertThatCode { testApi.getCountries() }
            .isInstanceOf(HolidayApiException::class.java)
            .hasNoCause()
        verify { response.code }
        verify { response.body }
        verify(exactly = 0) { body.close() }
        verify(exactly = 0) { jsonSerializer.decodeFromString<CountriesResponseDTO>(any()) }
    }

    @Test
    fun getHolidays_whenOkThrowExcaption_thenThrowHolidayApiException() {
        val ioException = IOException("")
        every { call.execute() } throws ioException
        Assertions.assertThatCode { testApi.getCountries() }
            .isInstanceOf(HolidayApiException::class.java)
            .hasCause(ioException)
    }

    private fun buildExpectedUrl() =
        ApiConst.HOLIDAYS_URL.toHttpUrl().newBuilder()
            .addQueryParameter("key", ApiConst.KEY)
            .build()
}