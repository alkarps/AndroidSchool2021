package ru.alkarps.android.school2021.hw18.data.storage

/**
 * Описание контракта БД HolidayApi
 */
object HolidayApiDbContract {
    const val DB_FILE_NAME: String = "holiday_api.db"
    const val DB_VERSION = 2

    /**
     * Список текущих таблиц
     */
    object Tables {
        const val LANGUAGE = "language"
        const val COUNTRY = "country"
        const val SUBDIVISION = "subdivision"
    }

    /**
     * Описание таблицы с языками
     */
    object Language {
        const val CODE = "code"
        const val NAME = "name"
    }

    /**
     * Описание таблицы с странами
     */
    object Country {
        const val CODE = "code"
        const val NAME = "name"
        const val LANGUAGES = "language_codes"
        const val FLAG = "flag"
    }

    /**
     * Описание таблицы с ТП
     */
    object Subdivision {
        const val CODE = "code"
        const val COUNTRY_CODE = "country_code"
        const val NAME = "name"
        const val LANGUAGES = "language_codes"
    }
}