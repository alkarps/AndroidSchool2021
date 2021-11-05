package ru.alkarps.android.school2021.hw18.data.settings

/**
 * Репозиторий настроек
 */
interface SettingsRepository {
    /**
     * Получение текущего кода языка
     *
     * @return код текущего языка или null
     */
    fun getCurrentLanguageCode(): String?
}