package ru.alkarps.android.school2021.hw18.data.language

import ru.alkarps.android.school2021.hw18.data.di.DataScope
import ru.alkarps.android.school2021.hw18.data.language.storage.LanguageStorage
import ru.alkarps.android.school2021.hw18.data.settings.SettingsRepository
import ru.alkarps.android.school2021.hw18.domen.language.LanguageRepository
import ru.alkarps.android.school2021.hw18.domen.model.Language

@DataScope
class ImplLanguageRepository(
    private val settings: SettingsRepository,
    private val storage: LanguageStorage
) : LanguageRepository {
    override fun getCurrentLanguage(): Language? =
        settings.getCurrentLanguageCode()?.let { storage.findLanguage(it) }

    override fun getLanguages(): List<Language>? = storage.getLanguages()

    override fun saveLanguages(languages: List<Language>) {
        storage.saveLanguages(languages)
    }
}