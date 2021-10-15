package ru.alkarps.android.school2021.hw15.timer.concurrency.impl

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import ru.alkarps.android.school2021.hw15.timer.concurrency.ConcurrencyTimerEngine
import java.util.concurrent.TimeUnit

class RxConcurrencyTimerEngine(update: () -> Unit) : ConcurrencyTimerEngine(update) {
    private lateinit var subscriber: Disposable
    override fun startThreads() {
        subscriber = Observable.interval(1, TimeUnit.SECONDS).subscribe { update() }
    }

    override fun destroyThreads() {
        if (!subscriber.isDisposed) subscriber.dispose()
    }

    override fun sendNextMessage() {
    }

    override fun finishingIfTimeOut() {
        subscriber.dispose()
    }
}