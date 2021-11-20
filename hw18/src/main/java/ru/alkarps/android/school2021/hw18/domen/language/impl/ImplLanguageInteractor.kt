package ru.alkarps.android.school2021.hw18.domen.language.impl

import ru.alkarps.android.school2021.hw18.domen.language.LanguageClient
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.language.LanguageInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository
import javax.inject.Inject

/**
 * Реализация [LanguageInteractor]
 *
 * @property settings репозиторий настроек приложения
 * @property repository репозиторий доступных языков
 * @property client клиент для получения доступных языков
 * @constructor Создает новую реализацию сервиса [LanguageInteractor]
 */
class ImplLanguageInteractor @Inject constructor(
    private val settings: SettingsRepository,
    private val repository: LanguageRepository,
    private val client: LanguageClient
) : LanguageInteractor {
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