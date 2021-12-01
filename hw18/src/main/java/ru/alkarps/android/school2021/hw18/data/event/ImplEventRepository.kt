package ru.alkarps.android.school2021.hw18.data.event

import ru.alkarps.android.school2021.hw18.data.event.converter.EventConverter
import ru.alkarps.android.school2021.hw18.data.storage.dao.EventDao
import ru.alkarps.android.school2021.hw18.domen.event.EventRepository
import ru.alkarps.android.school2021.hw18.domen.model.Event
import javax.inject.Inject

/**
 * Реализация [EventRepository]
 *
 * @property dao DAO для работы с БД в части событий
 * @property converter конвертер для преобразования из сущностей БД в сущность доменного слоя
 * @constructor Create empty Impl event repository
 */
class ImplEventRepository @Inject constructor(
    private val dao: EventDao,
    private val converter: EventConverter
) : EventRepository {
    override fun getByDate(date: String): List<Event> =
        dao.getByDate(date).map { converter.fromEntity(it) }

    override fun delete(uuid: String) {
        dao.delete(converter.toEntity(uuid))
    }

    override fun createOrUpdate(event: Event) {
        dao.insertOrUpdate(converter.toEntity(event))
    }
}