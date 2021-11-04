package ru.alkarps.android.school2021.hw18.domen.model.exception

/**
 * Ошибка при вызове HolidayApi
 *
 * @constructor Новый экземпляр ошибки
 */
class HolidayApiException : RuntimeException {
    constructor(throws: Throwable) : super(ERROR_MESSAGE, throws)
    constructor() : super(ERROR_MESSAGE)

    companion object {
        private const val ERROR_MESSAGE = "Сервис временно недоступен. Попробуйте через 5 минут."
    }
}