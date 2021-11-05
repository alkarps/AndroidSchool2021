package ru.alkarps.android.school2021.hw18.data.settings.impl

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.settings.SettingsRepository

class ImplSettingsRepositoryTest {
    private lateinit var preference: SharedPreferences
    private lateinit var repository: SettingsRepository

    @Before
    fun setUp() {
        preference = mockk()
        repository = ImplSettingsRepository(preference)
    }

    @Test
    fun getCurrentLanguageCode() {
        val code = "RU"
        every { preference.getString(any(), any()) } returns code
        assertThat(repository.getCurrentLanguageCode()).isEqualTo(code)
        verify { preference.getString("current_language", null) }
    }
}