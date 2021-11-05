package ru.alkarps.android.school2021.hw18.data.language

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.language.storage.LanguageStorage
import ru.alkarps.android.school2021.hw18.data.settings.SettingsRepository
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.model.Language

class ImplLanguageRepositoryTest {
    private lateinit var settings: SettingsRepository
    private lateinit var storage: LanguageStorage
    private lateinit var repository: LanguageRepository

    @Before
    fun setUp() {
        settings = mockk()
        storage = mockk()
        repository = ImplLanguageRepository(settings, storage)
    }

    @Test
    fun getCurrentLanguage_whenCurrentLanguageNotSelected_whenReturnNull() {
        every { settings.getCurrentLanguageCode() } returns null
        assertThat(repository.getCurrentLanguage()).isNull()
        verify { settings.getCurrentLanguageCode() }
    }

    @Test
    fun getCurrentLanguage_whenCurrentLanguageIsSelected_whenFindLanguageAtStorageAndReturnItIfExist() {
        val currentCode = "RU"
        val language = Language(currentCode, "name")
        every { settings.getCurrentLanguageCode() } returns currentCode
        every { storage.findLanguage(any()) } returns language
        assertThat(repository.getCurrentLanguage()).isEqualTo(language)
        verify { settings.getCurrentLanguageCode() }
        verify { storage.findLanguage(currentCode) }
    }

    @Test
    fun getLanguages_whenCall_thenDelegateToStorage() {
        val expected = listOf(Language("code", "name"))
        every { storage.getLanguages() } returns expected
        assertThat(repository.getLanguages()).isEqualTo(expected)
        verify { storage.getLanguages() }
    }

    @Test
    fun saveLanguages() {
        val languages = listOf(Language("code", "name"))
        justRun { storage.saveLanguages(any()) }
        assertThatCode { repository.saveLanguages(languages) }.doesNotThrowAnyException()
        verify { storage.saveLanguages(languages) }
    }
}