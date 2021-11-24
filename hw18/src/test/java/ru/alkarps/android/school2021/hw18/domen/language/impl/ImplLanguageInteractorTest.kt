package ru.alkarps.android.school2021.hw18.domen.language.impl

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageInteractor
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository

class ImplLanguageInteractorTest {
    private lateinit var settings: SettingsRepository
    private lateinit var repository: LanguageRepository
    private lateinit var client: LanguageClient
    private lateinit var testInteractor: LanguageInteractor

    @Before
    fun setUp() {
        settings = mockk()
        repository = mockk()
        client = mockk()
        testInteractor = ImplLanguageInteractor(settings, repository, client)
    }

    @Test
    fun getLanguages_whenRepositoryHasLanguages_thenReturnIt() {
        val expected = Language("RU", "Russian Federation")
        every { repository.getLanguages() } returns listOf(expected)
        assertThat(testInteractor.getLanguages()).isNotNull.hasSize(1).containsOnly(expected)
        verify { repository.getLanguages() }
        verify(exactly = 0) { client.getLanguages() }
    }

    @Test
    fun getLanguages_whenRepositoryNotHasLanguages_thenCallClient() {
        val expected = Language("RU", "Russian Federation")
        every { repository.getLanguages() } returns null
        every { client.getLanguages() } returns listOf(expected)
        justRun { repository.saveLanguages(any()) }
        assertThat(testInteractor.getLanguages()).isNotNull.hasSize(1).containsOnly(expected)
        verify { repository.getLanguages() }
        verify { client.getLanguages() }
        verify { repository.saveLanguages(listOf(expected)) }
    }

    @Test
    fun getCurrentLanguage_whenRepositoryFindLanguageByCode_thenReturnIt() {
        val code = "fr"
        val expected = Language(code, "French")
        every { settings.getCurrentLanguageCode() } returns code
        every { repository.findLanguage(any()) } returns expected
        assertThat(testInteractor.getCurrentLanguage()).isEqualTo(expected)
        verify { settings.getCurrentLanguageCode() }
        verify { repository.findLanguage(code) }
        verify(exactly = 0) { repository.getLanguages() }
    }

    @Test
    fun getCurrentLanguage_whenRepositoryNotHasCurrentLanguage_thenFindDefaultLanguage() {
        val code = "fr"
        val expected = Language(code, "French")
        every { settings.getCurrentLanguageCode() } returns code
        every { repository.findLanguage(any()) } returns null
        every { client.getLanguages() } returns listOf(expected)
        justRun { repository.saveLanguages(any()) }
        assertThat(testInteractor.getCurrentLanguage()).isEqualTo(expected)
        verify { settings.getCurrentLanguageCode() }
        verify { repository.findLanguage(code) }
        verify { client.getLanguages() }
        verify { repository.saveLanguages(listOf(expected)) }
    }

    @Test
    fun getCurrentLanguage_whenNotFindFindDefaultLanguage_thenReturnUnknownLanguage() {
        val code = "fr"
        val expected = Language("UNKNOWN", "UNKNOWN")
        every { settings.getCurrentLanguageCode() } returns code
        every { repository.findLanguage(any()) } returns null
        every { client.getLanguages() } returns emptyList()
        justRun { repository.saveLanguages(any()) }
        assertThat(testInteractor.getCurrentLanguage()).isEqualTo(expected)
        verify { settings.getCurrentLanguageCode() }
        verify { repository.findLanguage(code) }
        verify { client.getLanguages() }
        verify { repository.saveLanguages(listOf()) }
    }
}