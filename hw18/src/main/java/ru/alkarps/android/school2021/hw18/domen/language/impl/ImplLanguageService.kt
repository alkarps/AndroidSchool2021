package ru.alkarps.android.school2021.hw18.domen.language.impl

import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.language.LanguageService
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository

/**
 * Реализация [LanguageService]
 *
 * @property settings репозиторий настроек приложения
 * @property repository репозиторий доступных языков
 * @property client клиент для получения доступных языков
 * @constructor Создает новую реализацию сервиса [LanguageService]
 */
class ImplLanguageService(
    private val settings: SettingsRepository,
    private val repository: LanguageRepository,
    private val client: LanguageClient
) : LanguageService {
    override fun getCurrentLanguage(): Language {
        val currentLanguageCode = settings.getCurrentLanguageCode()
        return repository.findLanguage(currentLanguageCode)
            ?: getLanguagesFromRemoteWithSaving().find { it.code == currentLanguageCode }
            ?: Language(UNKNOWN_CODE, UNKNOWN_CODE)
    }

    override fun getLanguages(): List<Language> {
        return repository.getLanguages() ?: getLanguagesFromRemoteWithSaving()
    }

    private fun getLanguagesFromRemoteWithSaving(): List<Language> = client.getLanguages()
        .apply { repository.saveLanguages(this) }

    companion object {
        private const val UNKNOWN_CODE = "UNKNOWN"
    }
}