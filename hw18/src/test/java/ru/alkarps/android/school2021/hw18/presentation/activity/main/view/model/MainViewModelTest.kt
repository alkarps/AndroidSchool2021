package ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verifySequence
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.provider.HolidaysProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider
import ru.alkarps.android.school2021.hw18.util.toPeriod
import java.util.*

class MainViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val date = Calendar.getInstance()
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var holidaysProvider: HolidaysProvider
    private lateinit var processingObserver: Observer<Boolean>
    private lateinit var errorMessageObserver: Observer<String>
    private lateinit var holidaysObserver: Observer<List<DayWithHolidaysView>>
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        processingObserver = mockk()
        holidaysObserver = mockk()
        errorMessageObserver = mockk()

        schedulersProvider = mockk()
        holidaysProvider = mockk()
        viewModel = MainViewModel(schedulersProvider, holidaysProvider)

        every { schedulersProvider.back() } returns Schedulers.trampoline()
        every { schedulersProvider.main() } returns Schedulers.trampoline()
        justRun { processingObserver.onChanged(any()) }
        justRun { holidaysObserver.onChanged(any()) }
        justRun { errorMessageObserver.onChanged(any()) }

        viewModel.progress.observeForever(processingObserver)
        viewModel.holidays.observeForever(holidaysObserver)
        viewModel.errorMessages.observeForever(errorMessageObserver)
    }

    @Test
    fun loadHolidays_whenAllOk_thenSetHolidaysToLiveData() {
        val holidays = listOf(DayWithHolidaysView("", emptyList()))
        every { holidaysProvider.getHolidaysByPeriod(any()) } returns Single.just(holidays)

        assertThatCode { viewModel.loadHolidays(date) }.doesNotThrowAnyException()

        verifySequence {
            holidaysProvider.getHolidaysByPeriod(date.toPeriod())
            schedulersProvider.back()
            schedulersProvider.main()
            processingObserver.onChanged(false)
            holidaysObserver.onChanged(holidays)
            processingObserver.onChanged(true)
        }
    }

    @Test
    fun loadHolidays_whenCatchException_thenSetErrorToLiveData() {
        val caughtException = NullPointerException()
        every { holidaysProvider.getHolidaysByPeriod(any()) } returns Single.error(caughtException)
        assertThatCode { viewModel.loadHolidays(date) }.doesNotThrowAnyException()

        verifySequence {
            holidaysProvider.getHolidaysByPeriod(date.toPeriod())
            schedulersProvider.back()
            schedulersProvider.main()
            processingObserver.onChanged(false)
            holidaysObserver.onChanged(emptyList())
            errorMessageObserver.onChanged("При получении праздников произошла ошибка. Пожалуйста, повторите попытку позже")
            processingObserver.onChanged(true)
        }
    }
}