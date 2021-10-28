package ru.alkarps.android.school2021.hw18.view.provider

import io.reactivex.Scheduler

/**
 * Провайдер Schedulers для управления потоками в Rx
 */
interface SchedulersProvider {
    /**
     * [Scheduler] для фоновой работы
     *
     * @return [Scheduler]
     */
    fun back(): Scheduler

    /**
     * [Scheduler] для обновления экрана
     *
     * @return [Scheduler]
     */
    fun main(): Scheduler
}