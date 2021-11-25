package ru.alkarps.android.school2021.hw18.presentation.provider

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.presentation.model.EventView

/**
 * Контроллер событий
 */
interface EventsController {
    /**
     * Метод получения событий на указанную дату
     *
     * @param date дата
     * @return список найденых событий
     */
    fun getByDate(date: String): Single<List<EventView>>

    /**
     * Создание нового или изменение старого события
     *
     * @param event описание события
     * @return результат операции
     */
    fun createOrUpdate(event: EventView): Completable

    /**
     * Удаление события
     *
     * @param event удаляемое событие
     * @return результат операции
     */
    fun delete(event: EventView): Completable
}