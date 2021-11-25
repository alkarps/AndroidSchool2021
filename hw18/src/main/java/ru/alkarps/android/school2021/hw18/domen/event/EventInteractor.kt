package ru.alkarps.android.school2021.hw18.domen.event

import ru.alkarps.android.school2021.hw18.domen.model.Event

/**
 * Интерактор для работы с событиями
 */
interface EventInteractor {
    /**
     * Метод получения события на указанную дату
     *
     * @param date дата, на которую нужно вернуть события
     * @return список событий на указанную дату
     */
    fun getAllByDate(date: String): List<Event>

    /**
     * Метод удаления события по айди
     *
     * @param uuid айди события
     */
    fun delete(uuid: String)

    /**
     * Метод создания нового или обновления старого события
     *
     * @param event событие
     */
    fun createOrUpdate(event: Event)
}