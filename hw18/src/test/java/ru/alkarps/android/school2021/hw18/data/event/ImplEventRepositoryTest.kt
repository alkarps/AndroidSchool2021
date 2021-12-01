package ru.alkarps.android.school2021.hw18.data.event

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.Before
import org.junit.Test
import ru.alkarps.android.school2021.hw18.data.event.converter.EventConverter
import ru.alkarps.android.school2021.hw18.data.storage.dao.EventDao
import ru.alkarps.android.school2021.hw18.data.storage.entity.EventEntity
import ru.alkarps.android.school2021.hw18.domen.event.EventRepository
import ru.alkarps.android.school2021.hw18.domen.model.Event

class ImplEventRepositoryTest {
    private val event = Event("", "", "")
    private val eventEntity = EventEntity("", "", "")
    private lateinit var dao: EventDao
    private lateinit var converter: EventConverter
    private lateinit var repository: EventRepository

    @Before
    fun setUp() {
        dao = mockk()
        converter = mockk()
        repository = ImplEventRepository(dao, converter)
    }

    @Test
    fun getByDate() {
        val date = "date"
        every { dao.getByDate(any()) } returns listOf(eventEntity)
        every { converter.fromEntity(any()) } returns event
        assertThat(repository.getByDate(date)).isEqualTo(listOf(event))
        verifySequence {
            dao.getByDate(date)
            converter.fromEntity(eventEntity)
        }
        confirmVerified(dao, converter)
    }

    @Test
    fun delete() {
        val uuid = "uuid"
        justRun { dao.delete(any()) }
        every { converter.toEntity(any<String>()) } returns eventEntity
        assertThatCode { repository.delete(uuid) }.doesNotThrowAnyException()
        verifySequence {
            converter.toEntity(uuid)
            dao.delete(eventEntity)
        }
        confirmVerified(dao, converter)
    }

    @Test
    fun createOrUpdate() {
        justRun { dao.insertOrUpdate(any()) }
        every { converter.toEntity(any<Event>()) } returns eventEntity
        assertThatCode { repository.createOrUpdate(event) }.doesNotThrowAnyException()
        verifySequence {
            converter.toEntity(event)
            dao.insertOrUpdate(eventEntity)
        }
        confirmVerified(dao, converter)
    }
}