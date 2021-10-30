package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import ru.alkarps.android.school2021.hw18.domen.model.Period
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

class MainViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var holidaysProvider: HolidaysProvider
    private lateinit var processingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<Throwable>
    private lateinit var holidaysObserver: Observer<List<DayWithHolidaysView>>
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        processingObserver = mockk()
        errorObserver = mockk()
        holidaysObserver = mockk()

        schedulersProvider = mockk()
        holidaysProvider = mockk()
        viewModel = MainViewModel(schedulersProvider, holidaysProvider)

        every { schedulersProvider.back() } returns Schedulers.trampoline()
        every { schedulersProvider.main() } returns Schedulers.trampoline()
        justRun { processingObserver.onChanged(any()) }
        justRun { errorObserver.onChanged(any()) }
        justRun { holidaysObserver.onChanged(any()) }

        viewModel.errorLiveData.observeForever(errorObserver)
        viewModel.progressLiveData.observeForever(processingObserver)
        viewModel.holidaysLiveData.observeForever(holidaysObserver)
    }

    @After
    fun tearDown() {
        verify { holidaysProvider.getHolidaysByPeriod(Period(2020)) }
        verify { schedulersProvider.back() }
        verify { schedulersProvider.main() }
    }

    @Test
    fun loadHolidays_whenAllOk_thenSetHolidaysToLiveData() {
        val holidays = listOf(DayWithHolidaysView("", emptyList()))
        every { holidaysProvider.getHolidaysByPeriod(any()) } returns Single.just(holidays)

        assertThatCode { viewModel.loadHolidays() }.doesNotThrowAnyException()

        verifySequence {
            processingObserver.onChanged(true)
            holidaysObserver.onChanged(holidays)
            processingObserver.onChanged(false)
        }
    }

    @Test
    fun loadHolidays_whenCatchException_thenSetErrorToLiveData() {
        val caughtException = NullPointerException()
        every { holidaysProvider.getHolidaysByPeriod(any()) } returns Single.error(caughtException)

        assertThatCode { viewModel.loadHolidays() }.doesNotThrowAnyException()

        verifySequence {
            processingObserver.onChanged(true)
            errorObserver.onChanged(caughtException)
            processingObserver.onChanged(false)
        }
    }
}