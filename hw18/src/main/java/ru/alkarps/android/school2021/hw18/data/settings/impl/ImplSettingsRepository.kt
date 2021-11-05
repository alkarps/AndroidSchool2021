package ru.alkarps.android.school2021.hw18.data.settings.impl

import android.content.SharedPreferences
import ru.alkarps.android.school2021.hw18.data.settings.SettingsRepository

/**
 * Реализация [SettingsRepository]
 *
 * @property preference настройки
 */
class ImplSettingsRepository(private val preference: SharedPreferences) : SettingsRepository {
    override fun getCurrentLanguageCode(): String? = preference.getString("current_language", null)
}