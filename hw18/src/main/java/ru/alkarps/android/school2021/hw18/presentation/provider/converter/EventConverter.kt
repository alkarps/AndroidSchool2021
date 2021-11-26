package ru.alkarps.android.school2021.hw18.presentation.provider.converter

import ru.alkarps.android.school2021.hw18.domen.model.Event
import ru.alkarps.android.school2021.hw18.presentation.model.EventView

/**
 * Конвертер событий между сущностями доменным и слоем представления
 */
interface EventConverter {
    /**
     * Метод конвертации в доменную модель
     *
     * @param event сущность события в слое представления
     * @return сущность события в доменном слое
     */
    fun fromView(event: EventView): Event

    /**
     * Метод конвертации в модель слоя представления
     *
     * @param events сущности события в доменном слое
     * @return сущности события в слое представления
     */
    fun toView(events: List<Event>): List<EventView>
}