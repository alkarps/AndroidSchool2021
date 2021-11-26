package ru.alkarps.android.school2021.hw18.domen.event.impl

import ru.alkarps.android.school2021.hw18.domen.event.EventInteractor
import ru.alkarps.android.school2021.hw18.domen.event.EventRepository
import ru.alkarps.android.school2021.hw18.domen.model.Event
import javax.inject.Inject

/**
 * Реализация [EventInteractor]
 *
 * @property repository репозиторий для работы с событиями
 */
class ImplEventInteractor @Inject constructor(
    private val repository: EventRepository
) : EventInteractor {
    override fun getByDate(date: String): List<Event> {
        return repository.getByDate(date)
    }

    override fun delete(uuid: String) {
        repository.delete(uuid)
    }

    override fun createOrUpdate(event: Event) {
        repository.createOrUpdate(event)
    }
}