package ru.alkarps.android.school2021.hw18.domen.settings

/**
 * Репозиторий настроек
 */
interface SettingsRepository {
    /**
     * Получение текущего кода языка
     *
     * @return код текущего языка или дефолтный код
     */
    fun getCurrentLanguageCode(): String
}