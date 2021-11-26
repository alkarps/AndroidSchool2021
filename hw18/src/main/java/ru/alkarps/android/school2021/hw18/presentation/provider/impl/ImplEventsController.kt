package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.alkarps.android.school2021.hw18.domen.event.EventInteractor
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.EventConverter
import javax.inject.Inject

/**
 * Реализация [EventsController]
 *
 * @property interactor интерактор событий
 * @property converter конвертер событий
 * @constructor Create empty Impl events controller
 */
class ImplEventsController @Inject constructor(
    private val interactor: EventInteractor,
    private val converter: EventConverter
) : EventsController {
    override fun getByDate(date: String): Single<List<EventView>> =
        Single.fromCallable { interactor.getByDate(date) }
            .map { converter.toView(it) }

    override fun createOrUpdate(event: EventView): Completable = converter.fromView(event).let {
        Completable.fromAction { interactor.createOrUpdate(it) }
    }

    override fun delete(event: EventView): Completable =
        Completable.fromAction { interactor.delete(event.uuid) }
}