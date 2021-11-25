package ru.alkarps.android.school2021.hw18.data.event

import ru.alkarps.android.school2021.hw18.domen.event.EventRepository
import ru.alkarps.android.school2021.hw18.domen.model.Event
import javax.inject.Inject

/**
 * Реализация [EventRepository]
 *
 * @constructor Create empty Impl event repository
 */
class ImplEventRepository @Inject constructor() : EventRepository {
    private val storage = mutableListOf<Event>()
    override fun getByDate(date: String): List<Event> {
        return storage.filter { it.date == date }
    }

    override fun delete(uuid: String) {
        storage.find { it.uuid == uuid }?.apply { storage.remove(this) }
    }

    override fun createOrUpdate(event: Event) {
        delete(event.uuid)
        storage.add(event)
    }
}