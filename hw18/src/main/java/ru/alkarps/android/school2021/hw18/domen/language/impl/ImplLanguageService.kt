package ru.alkarps.android.school2021.hw18.domen.language.impl

import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService
import ru.alkarps.android.school2021.hw18.domen.model.Language

/**
 * Реализация [LanguageService]
 *
 * @property repository репозиторий доступных языков
 * @property client клиент для получения доступных языков
 * @constructor Создает новую реализацию сервиса [LanguageService]
 */
class ImplLanguageService(
    private val repository: LanguageRepository,
    private val client: LanguageClient
) : LanguageService {
    override fun getCurrentLanguage(): Language {
        return repository.getCurrentLanguage()
            ?: getLanguages().find { it.code == DEFAULT_CODE }
            ?: Language(UNKNOWN_CODE, UNKNOWN_CODE)
    }

    override fun getLanguages(): List<Language> {
        return repository.getLanguages() ?: client.getLanguages()
    }

    companion object {
        private const val DEFAULT_CODE = "RU"
        private const val UNKNOWN_CODE = "UNKNOWN"
    }
}