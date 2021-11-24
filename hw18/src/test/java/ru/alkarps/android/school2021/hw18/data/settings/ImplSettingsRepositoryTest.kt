package ru.alkarps.android.school2021.hw18.data.settings

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository

class ImplSettingsRepositoryTest {
    private lateinit var preference: SharedPreferences
    private lateinit var repository: SettingsRepository

    @Before
    fun setUp() {
        preference = mockk()
        repository = ImplSettingsRepository(preference)
    }

    @Test
    fun getCurrentLanguageCode_whenCodeIsExist_thenReturnIt() {
        assertExist(CURRENT_LANGUAGE_KEY) { repository.getCurrentLanguageCode() }
    }

    @Test
    fun getCurrentCountryCode_whenCodeIsExist_thenReturnIt() {
        assertExist(CURRENT_COUNTRY_KEY) { repository.getCurrentCountryCode() }
    }

    @Test
    fun getCurrentSubdivisionCode_whenCodeIsExist_thenReturnIt() {
        assertExist(CURRENT_SUBDIVISION_KEY) { repository.getCurrentSubdivisionCode() }
    }

    private fun assertExist(key: String, testMethod: () -> String) {
        val code = "Code"
        every { preference.getString(any(), any()) } returns code
        assertThat(testMethod()).isEqualTo(code)
        verify { preference.getString(key, DEFAULT_CODE) }
    }

    @Test
    fun getCurrentLanguageCode_whenCodeIsNotExist_thenReturnDefaultCode() {
        assertNotExist(CURRENT_LANGUAGE_KEY) { repository.getCurrentLanguageCode() }
    }

    @Test
    fun getCurrentCountryCode_whenCodeIsNotExist_thenReturnDefaultCode() {
        assertNotExist(CURRENT_COUNTRY_KEY) { repository.getCurrentCountryCode() }
    }

    @Test
    fun getCurrentSubdivisionCode_whenCodeIsNotExist_thenReturnDefaultCode() {
        assertNotExist(CURRENT_SUBDIVISION_KEY) { repository.getCurrentSubdivisionCode() }
    }

    private fun assertNotExist(key: String, testMethod: () -> String) {
        every { preference.getString(any(), any()) } returns null
        assertThat(testMethod()).isEqualTo(DEFAULT_CODE)
        verify { preference.getString(key, DEFAULT_CODE) }
    }

    private companion object {
        private const val CURRENT_LANGUAGE_KEY = "current_language"
        private const val CURRENT_COUNTRY_KEY = "current_country"
        private const val CURRENT_SUBDIVISION_KEY = "current_subdivision"
        private const val DEFAULT_CODE = "ru"
    }
}