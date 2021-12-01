package ru.alkarps.android.school2021.hw18.data.language

import ru.alkarps.android.school2021.hw18.data.di.DataScope
import ru.alkarps.android.school2021.hw18.data.language.converter.LanguageConverter
import ru.alkarps.android.school2021.hw18.data.storage.dao.LanguageDao
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.model.Language
import javax.inject.Inject

/**
 * Реализация [LanguageRepository]
 *
 * @property dao DAO класс управления БД для работы с доступными языками
 * @property converter конвертер доступных языков в дата слое
 */
@DataScope
class ImplLanguageRepository @Inject constructor(
    private val dao: LanguageDao,
    private val converter: LanguageConverter
) : LanguageRepository {
    override fun getLanguages(): List<Language>? {
        return dao.getAll().map { converter.fromEntity(it) }.let { if (it.isEmpty()) null else it }
    }

    override fun saveLanguages(languages: List<Language>) {
        dao.deleteAll()
        dao.insertAll(converter.toEntity(languages))
    }

    override fun findLanguage(code: String): Language? {
        return dao.findByCode(code.lowercase())?.let { converter.fromEntity(it) }
    }
}