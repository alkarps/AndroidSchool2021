package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

/**
 * Реализация [SchedulersProvider]
 *
 * @constructor Новый объект реализации [SchedulersProvider]
 */
class ImplSchedulersProvider : SchedulersProvider {
    override fun back(): Scheduler = Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}