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
    /**
     * Получение текущего кода страны
     *
     * @return код страны или дефолтный код
     */
    fun getCurrentCountryCode(): String
    /**
     * Получение текущего кода территориального подразделения
     *
     * @return код текущего территориального подразделения или дефолтный код
     */
    fun getCurrentSubdivisionCode(): String
}