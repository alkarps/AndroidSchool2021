package ru.alkarps.android.school2021.hw18.data.language

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.storage.dao.LanguageDao
import ru.alkarps.android.school2021.hw18.data.storage.entity.LanguageEntity
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.model.Language

class ImplLanguageRepositoryTest {
    private val language = Language("", "")
    private val languageEntity = LanguageEntity("", "")
    private lateinit var dao: LanguageDao
    private lateinit var converter: LanguageConverter
    private lateinit var repository: LanguageRepository

    @Before
    fun setUp() {
        dao = mockk()
        converter = mockk()
        repository = ImplLanguageRepository(dao, converter)
    }

    @Test
    fun getLanguages_whenIsEmpty_thenReturnNull() {
        every { dao.getAll() } returns emptyList()
        assertThat(repository.getLanguages()).isNull()
        verifySequence { dao.getAll() }
        confirmVerified(dao, converter)
    }

    @Test
    fun getLanguages_whenIsNotEmpty_thenReturnIt() {
        every { dao.getAll() } returns listOf(languageEntity)
        every { converter.fromEntity(any()) } returns language
        assertThat(repository.getLanguages()).isEqualTo(listOf(language))
        verifySequence {
            dao.getAll()
            converter.fromEntity(languageEntity)
        }
        confirmVerified(dao, converter)
    }

    @Test
    fun saveLanguages() {
        justRun { dao.deleteAll() }
        justRun { dao.insertAll(any()) }
        every { converter.toEntity(any()) } returns listOf(languageEntity)
        assertThatCode { repository.saveLanguages(listOf(language)) }.doesNotThrowAnyException()
        verifySequence {
            dao.deleteAll()
            converter.toEntity(listOf(language))
            dao.insertAll(listOf(languageEntity))
        }
        confirmVerified(dao, converter)
    }

    @Test
    fun findLanguage_whenNotFound_thenReturnNull() {
        val code = "Code"
        every { dao.findByCode(any()) } returns null
        assertThat(repository.findLanguage(code)).isNull()
        verifySequence { dao.findByCode(code.lowercase()) }
        confirmVerified(dao, converter)
    }

    @Test
    fun findLanguage_whenFound_thenReturnIt() {
        val code = "Code"
        every { dao.findByCode(any()) } returns languageEntity
        every { converter.fromEntity(any()) } returns language
        assertThat(repository.findLanguage(code)).isEqualTo(language)
        verifySequence {
            dao.findByCode(code.lowercase())
            converter.fromEntity(languageEntity)
        }
        confirmVerified(dao, converter)
    }
}