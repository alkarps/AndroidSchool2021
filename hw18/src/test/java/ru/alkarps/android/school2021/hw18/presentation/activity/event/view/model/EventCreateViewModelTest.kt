package ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

class EventCreateViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val event = EventView("", "", "", "", "")
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var eventsController: EventsController
    private lateinit var successObserver: Observer<Boolean>
    private lateinit var errorMessagesObserver: Observer<String>
    private lateinit var model: EventCreateViewModel

    @Before
    fun setUp() {
        schedulersProvider = mockk()
        eventsController = mockk()

        successObserver = mockk()
        errorMessagesObserver = mockk()

        every { schedulersProvider.back() } returns Schedulers.trampoline()
        every { schedulersProvider.main() } returns Schedulers.trampoline()
        justRun { successObserver.onChanged(any()) }
        justRun { errorMessagesObserver.onChanged(any()) }
        model = EventCreateViewModel(schedulersProvider, eventsController)

        model.success.observeForever(successObserver)
        model.errorMessages.observeForever(errorMessagesObserver)
    }

    @Test
    fun create_whenAllOk_thenSuccessIsTrue() {
        every { eventsController.createOrUpdate(any()) } returns Completable.complete()
        Assertions.assertThatCode { model.create(event) }.doesNotThrowAnyException()

        verifySequence {
            eventsController.createOrUpdate(event)
            schedulersProvider.back()
            schedulersProvider.main()
            successObserver.onChanged(true)
        }
        confirmVerified(
            eventsController,
            schedulersProvider,
            successObserver,
            errorMessagesObserver
        )
    }

    @Test
    fun create_whenAnyThrowException_thenErrorMessage() {
        every { eventsController.createOrUpdate(any()) } returns Completable.error(
            NullPointerException()
        )
        Assertions.assertThatCode { model.create(event) }.doesNotThrowAnyException()

        verifySequence {
            eventsController.createOrUpdate(event)
            schedulersProvider.back()
            schedulersProvider.main()
            errorMessagesObserver.onChanged("При получении создании события произошла ошибка. Пожалуйста, повторите попытку позже")
        }
        confirmVerified(
            eventsController,
            schedulersProvider,
            successObserver,
            errorMessagesObserver
        )
    }
}