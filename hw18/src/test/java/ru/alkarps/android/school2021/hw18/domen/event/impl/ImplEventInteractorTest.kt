package ru.alkarps.android.school2021.hw18.domen.event.impl

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.domen.event.EventInteractor
import ru.alkarps.android.school2021.hw18.domen.event.EventRepository
import ru.alkarps.android.school2021.hw18.domen.model.Event

class ImplEventInteractorTest {
    private val event = Event("uuid", "name", "date", "startTime", "location")
    private lateinit var repository: EventRepository
    private lateinit var interactor: EventInteractor

    @Before
    fun setUp() {
        repository = mockk()
        interactor = ImplEventInteractor(repository)
    }

    @Test
    fun getByDate() {
        val date = "Date"
        every { repository.getByDate(any()) } returns listOf(event)
        assertThat(interactor.getByDate(date)).isEqualTo(listOf(event))
        verify { repository.getByDate(date) }
    }

    @Test
    fun delete() {
        val uuid = "Uuid"
        justRun { repository.delete(any()) }
        assertThatCode { interactor.delete(uuid) }.doesNotThrowAnyException()
        verify { repository.delete(uuid) }
    }

    @Test
    fun createOrUpdate() {
        justRun { repository.createOrUpdate(any()) }
        assertThatCode { interactor.createOrUpdate(event) }.doesNotThrowAnyException()
        verify { repository.createOrUpdate(event) }
    }
}