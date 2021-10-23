package ru.alkarps.android.school2021.hw18.domen.language.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService
import ru.alkarps.android.school2021.hw18.domen.model.Language

class ImplLanguageServiceTest {
    private lateinit var repository: LanguageRepository
    private lateinit var client: LanguageClient
    private lateinit var testService: LanguageService

    @Before
    fun setUp() {
        repository = mockk()
        client = mockk()
        testService = ImplLanguageService(repository, client)
    }

    @Test
    fun getLanguages_whenRepositoryHasLanguages_thenReturnIt() {
        val expected = Language("RU", "Russian Federation")
        every { repository.getLanguages() } returns listOf(expected)
        assertThat(testService.getLanguages()).isNotNull.hasSize(1).containsOnly(expected)
        verify { repository.getLanguages() }
        verify(exactly = 0) { client.getLanguages() }
    }

    @Test
    fun getLanguages_whenRepositoryNotHasLanguages_thenCallClient() {
        val expected = Language("RU", "Russian Federation")
        every { repository.getLanguages() } returns null
        every { client.getLanguages() } returns listOf(expected)
        assertThat(testService.getLanguages()).isNotNull.hasSize(1).containsOnly(expected)
        verify { repository.getLanguages() }
        verify { client.getLanguages() }
    }

    @Test
    fun getCurrentLanguage_whenRepositoryHasCurrentLanguage_thenReturnIt() {
        val expected = Language("EN", "USA")
        every { repository.getCurrentLanguage() } returns expected
        assertThat(testService.getCurrentLanguage()).isEqualTo(expected)
        verify { repository.getCurrentLanguage() }
        verify(exactly = 0) { repository.getLanguages() }
    }

    @Test
    fun getCurrentLanguage_whenRepositoryNotHasCurrentLanguage_thenFindDefaultLanguage() {
        val expected = Language("RU", "Russian Federation")
        every { repository.getCurrentLanguage() } returns null
        every { repository.getLanguages() } returns listOf(expected)
        assertThat(testService.getCurrentLanguage()).isEqualTo(expected)
        verify { repository.getCurrentLanguage() }
        verify { repository.getLanguages() }
    }

    @Test
    fun getCurrentLanguage_whenNotFindFindDefaultLanguage_thenReturnUnknownLanguage() {
        val expected = Language("UNKNOWN", "UNKNOWN")
        every { repository.getCurrentLanguage() } returns null
        every { repository.getLanguages() } returns emptyList()
        assertThat(testService.getCurrentLanguage()).isEqualTo(expected)
        verify { repository.getCurrentLanguage() }
        verify { repository.getLanguages() }
    }
}