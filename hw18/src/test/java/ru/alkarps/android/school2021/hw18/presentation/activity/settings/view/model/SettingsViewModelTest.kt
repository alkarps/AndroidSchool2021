package ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.SubdivisionView
import ru.alkarps.android.school2021.hw18.presentation.provider.CountriesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.LanguagesProvider
import ru.alkarps.android.school2021.hw18.presentation.provider.SchedulersProvider

class SettingsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var languagesProvider: LanguagesProvider
    private lateinit var countriesProvider: CountriesProvider
    private lateinit var languagesObserver: Observer<List<Language>>
    private lateinit var countriesObserver: Observer<List<CountryView>>
    private lateinit var subdivisionsObserver: Observer<List<SubdivisionView>>
    private lateinit var errorObserver: Observer<Throwable>
    private lateinit var model: SettingsViewModel

    @Before
    fun setUp() {
        schedulersProvider = mockk()
        languagesProvider = mockk()
        countriesProvider = mockk()

        languagesObserver = mockk()
        countriesObserver = mockk()
        subdivisionsObserver = mockk()
        errorObserver = mockk()

        every { schedulersProvider.back() } returns Schedulers.trampoline()
        every { schedulersProvider.main() } returns Schedulers.trampoline()
        justRun { languagesObserver.onChanged(any()) }
        justRun { countriesObserver.onChanged(any()) }
        justRun { subdivisionsObserver.onChanged(any()) }
        justRun { errorObserver.onChanged(any()) }
        model = SettingsViewModel(schedulersProvider, languagesProvider, countriesProvider)

        model.languagesLiveData.observeForever(languagesObserver)
        model.countriesLiveData.observeForever(countriesObserver)
        model.subdivisionsLiveData.observeForever(subdivisionsObserver)
        model.errorLiveData.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        verify { schedulersProvider.back() }
        verify { schedulersProvider.main() }
    }

    @Test
    fun loadAllData_whenAllOk_thenLoadData() {
        val languages = listOf(Language("", ""))
        every { languagesProvider.getLanguages() } returns Single.just(languages)
        val countries = listOf(CountryView("", ""))
        every { countriesProvider.getCountries() } returns Single.just(countries)
        val subdivisions = listOf(SubdivisionView("", ""))
        every { countriesProvider.getSubdivisionsForCurrentCountry() } returns
                Single.just(subdivisions)

        Assertions.assertThatCode { model.loadAllData() }.doesNotThrowAnyException()

        verify { languagesProvider.getLanguages() }
        verify { countriesProvider.getCountries() }
        verify { countriesProvider.getSubdivisionsForCurrentCountry() }
        verify { languagesObserver.onChanged(languages) }
        verify { countriesObserver.onChanged(countries) }
        verify { subdivisionsObserver.onChanged(subdivisions) }
    }

    @Test
    fun loadAllData_whenAnyThrowException_thenErrorAddToLiveData() {
        val languages = listOf(Language("", ""))
        every { languagesProvider.getLanguages() } returns Single.just(languages)
        val countries = listOf(CountryView("", ""))
        every { countriesProvider.getCountries() } returns Single.just(countries)
        val error = NullPointerException("")
        every { countriesProvider.getSubdivisionsForCurrentCountry() } returns Single.error(error)

        Assertions.assertThatCode { model.loadAllData() }.doesNotThrowAnyException()

        verify { languagesProvider.getLanguages() }
        verify { countriesProvider.getCountries() }
        verify { countriesProvider.getSubdivisionsForCurrentCountry() }
        verify { languagesObserver.onChanged(languages) }
        verify { countriesObserver.onChanged(countries) }
        verify { errorObserver.onChanged(error) }
    }

    @Test
    fun updateSubdivisions_whenAllOk_thenLoadData() {
        val code = "Code"
        val subdivisions = listOf(SubdivisionView("", ""))
        every { countriesProvider.getSubdivisions(any()) } returns Single.just(subdivisions)

        Assertions.assertThatCode { model.updateSubdivisions(code) }.doesNotThrowAnyException()

        verify { countriesProvider.getSubdivisions(code) }
        verify { subdivisionsObserver.onChanged(subdivisions) }
    }

    @Test
    fun updateSubdivisions_whenAnyThrowException_thenErrorAddToLiveData() {
        val code = "Code"
        val error = NullPointerException("")
        every { countriesProvider.getSubdivisions(any()) } returns Single.error(error)

        Assertions.assertThatCode { model.updateSubdivisions(code) }.doesNotThrowAnyException()

        verify { countriesProvider.getSubdivisions(code) }
        verify { errorObserver.onChanged(error) }
    }
}