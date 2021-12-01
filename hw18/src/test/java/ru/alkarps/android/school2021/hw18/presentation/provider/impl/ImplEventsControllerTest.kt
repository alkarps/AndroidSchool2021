package ru.alkarps.android.school2021.hw18.presentation.provider.impl

import io.mockk.*
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.event.EventInteractor
import ru.alkarps.android.school2021.hw18.domen.model.Event
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.presentation.provider.EventsController
import ru.alkarps.android.school2021.hw18.presentation.provider.converter.EventConverter

class ImplEventsControllerTest {
    private val event = Event("uuid", "name", "date", "startTime", "location")
    private val eventView = EventView("name", "date", "startTime", "location", "uuid")
    private lateinit var interactor: EventInteractor
    private lateinit var converter: EventConverter
    private lateinit var controller: EventsController

    @Before
    fun setUp() {
        interactor = mockk()
        converter = mockk()
        controller = ImplEventsController(interactor, converter)
    }

    @Test
    fun getByDate() {
        val date = "Date"
        every { interactor.getByDate(any()) } returns listOf(event)
        every { converter.toView(any()) } returns listOf(eventView)

        controller.getByDate(date).test().assertValue(listOf(eventView))

        verifySequence {
            interactor.getByDate(date)
            converter.toView(listOf(event))
        }
        confirmVerified(interactor, converter)
    }

    @Test
    fun createOrUpdate() {
        justRun { interactor.createOrUpdate(any()) }
        every { converter.fromView(any()) } returns event

        controller.createOrUpdate(eventView).test().assertNoErrors().assertNoValues()

        verifySequence {
            converter.fromView(eventView)
            interactor.createOrUpdate(event)
        }
        confirmVerified(interactor, converter)
    }

    @Test
    fun delete() {
        justRun { interactor.delete(any()) }

        controller.delete(eventView).test().assertNoErrors().assertNoValues()

        verifySequence {
            interactor.delete(eventView.uuid)
        }
        confirmVerified(interactor, converter)
    }
}