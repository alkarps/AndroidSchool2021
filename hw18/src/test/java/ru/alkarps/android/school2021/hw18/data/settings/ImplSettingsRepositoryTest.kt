package ru.alkarps.android.school2021.hw18.data.settings

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.settings.ImplSettingsRepository.Companion.CURRENT_LANGUAGE_KEY
import ru.alkarps.android.school2021.hw18.data.settings.ImplSettingsRepository.Companion.DEFAULT_LANGUAGE_CODE
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
        val code = "en"
        every { preference.getString(any(), any()) } returns code
        assertThat(repository.getCurrentLanguageCode()).isEqualTo(code)
        verify { preference.getString(CURRENT_LANGUAGE_KEY, DEFAULT_LANGUAGE_CODE) }
    }

    @Test
    fun getCurrentLanguageCode_whenCodeIsNotExist_thenReturnDefaultCode() {
        every { preference.getString(any(), any()) } returns null
        assertThat(repository.getCurrentLanguageCode()).isEqualTo(DEFAULT_LANGUAGE_CODE)
        verify { preference.getString(CURRENT_LANGUAGE_KEY, DEFAULT_LANGUAGE_CODE) }
    }
}