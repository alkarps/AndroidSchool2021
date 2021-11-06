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
    override fun getCurrentLanguageCode(): String = getValue(CURRENT_LANGUAGE_KEY)

    override fun getCurrentCountryCode(): String = getValue(CURRENT_COUNTRY_KEY)

    override fun getCurrentSubdivisionCode(): String = getValue(CURRENT_SUBDIVISION_KEY)

    private fun getValue(key: String): String =
        preference.getString(key, DEFAULT_CODE) ?: DEFAULT_CODE

    companion object {
        const val CURRENT_LANGUAGE_KEY = "current_language"
        const val CURRENT_COUNTRY_KEY = "current_country"
        const val CURRENT_SUBDIVISION_KEY = "current_subdivision"
        const val DEFAULT_CODE = "ru"
    }
}