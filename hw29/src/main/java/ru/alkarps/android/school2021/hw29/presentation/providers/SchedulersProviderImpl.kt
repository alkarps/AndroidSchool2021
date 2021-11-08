package ru.alkarps.android.school2021.hw29.presentation.providers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulersProviderImpl : SchedulersProvider {
    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    override fun back(): Scheduler = Schedulers.io()
}