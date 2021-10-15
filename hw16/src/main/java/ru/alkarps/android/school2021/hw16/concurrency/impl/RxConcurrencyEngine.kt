package ru.alkarps.android.school2021.hw16.concurrency.impl

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.alkarps.android.school2021.hw16.concurrency.ConcurrencyEngine

class RxConcurrencyEngine : ConcurrencyEngine {
    private lateinit var subscriber: Disposable
    override fun doJob(job: () -> Unit) {
        subscriber = Single.fromCallable(job).subscribeOn(Schedulers.single()).subscribe()
    }

    override fun destroy() {
        subscriber.dispose()
    }
}