package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import javax.inject.Inject

/**
 * Реализация [SchedulersProvider]
 *
 * @constructor Новый объект реализации [SchedulersProvider]
 */
class ImplSchedulersProvider @Inject constructor() : SchedulersProvider {
    override fun back(): Scheduler = Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}