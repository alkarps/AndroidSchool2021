package ru.alkarps.android.school2021.hw18.data.storage

/**
 * Описание контракта БД HolidayApi
 */
object HolidayApiDbContract {
    const val DB_FILE_NAME: String = "holiday_api.db"
    const val DB_VERSION = 3

    /**
     * Список текущих таблиц
     */
    object Tables {
        const val LANGUAGE = "language"
        const val COUNTRY = "country"
        const val SUBDIVISION = "subdivision"
        const val EVENT = "event"
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

    /**
     * Описание таблицы с Событиями
     */
    object Event {
        const val UUID = "uuid"
        const val NAME = "name"
        const val DATE = "date"
        const val START_TIME = "start_time"
        const val LOCATION = "location"
    }
}