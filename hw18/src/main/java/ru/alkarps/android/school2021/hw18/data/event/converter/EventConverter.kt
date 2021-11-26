package ru.alkarps.android.school2021.hw18.data.event.converter

import ru.alkarps.android.school2021.hw18.data.storage.entity.EventEntity
import ru.alkarps.android.school2021.hw18.domen.model.Event

/**
 * Конвертер представления событий в дата слое в представление событий бизнес слоя
 */
interface EventConverter {
    /**
     * Метод конвертации id в [EventEntity]
     *
     * @param uuid id события
     * @return событие в представлении дата слоя
     */
    fun toEntity(uuid: String): EventEntity
    /**
     * Метод конвертации [Event] в [EventEntity]
     *
     * @param event событие в представлении бизнес слоя
     * @return событие в представлении дата слоя
     */
    fun toEntity(event: Event): EventEntity
    /**
     * Метод конвертации [EventEntity] в [Event]
     *
     * @param event событие в представлении дата слоя
     * @return событие в представлении бизнес слоя
     */
    fun fromEntity(event: EventEntity): Event
}