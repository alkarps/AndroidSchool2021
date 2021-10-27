package ru.alkarps.android.school2021.hw15.timer.display

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.alkarps.android.school2021.hw15.timer.TimerDisplayFragment
import java.util.concurrent.TimeUnit

class RxTimerDisplayFragment : TimerDisplayFragment() {
    private lateinit var subscriber: Disposable

    override fun onStart() {
        super.onStart()
        subscriber = Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .map { currentTime.decrementAndGet() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (currentTime.get() >= 0) updateDisplayTime() else {
                    subscriber.dispose()
                    returnControlPanel()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!subscriber.isDisposed) subscriber.dispose()
    }

    override fun updateDisplayAndSendNextMessage() { }

    override fun exiting() {}
}