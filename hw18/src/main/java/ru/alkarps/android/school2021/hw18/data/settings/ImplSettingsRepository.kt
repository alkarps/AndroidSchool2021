package ru.alkarps.android.school2021.hw18.data.settings

import android.content.SharedPreferences
import ru.alkarps.android.school2021.hw18.domen.settings.SettingsRepository
import javax.inject.Inject

/**
 * Реализация [SettingsRepository]
 *
 * @property preference настройки
 */
class ImplSettingsRepository @Inject constructor(
    private val preference: SharedPreferences
) : SettingsRepository {
    override fun getCurrentLanguageCode(): String =
        preference.getString(CURRENT_LANGUAGE_KEY, DEFAULT_LANGUAGE_CODE) ?: DEFAULT_LANGUAGE_CODE

    companion object {
        const val CURRENT_LANGUAGE_KEY = "current_language"
        const val DEFAULT_LANGUAGE_CODE = "ru"
    }
}