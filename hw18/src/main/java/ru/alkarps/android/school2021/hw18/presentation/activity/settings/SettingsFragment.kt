package ru.alkarps.android.school2021.hw18.presentation.activity.settings

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import ru.alkarps.android.school2021.hw18.HolidayApiApplication
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.data.settings.ImplSettingsRepository.Companion.CURRENT_COUNTRY_KEY
import ru.alkarps.android.school2021.hw18.data.settings.ImplSettingsRepository.Companion.CURRENT_LANGUAGE_KEY
import ru.alkarps.android.school2021.hw18.data.settings.ImplSettingsRepository.Companion.CURRENT_SUBDIVISION_KEY
import ru.alkarps.android.school2021.hw18.data.settings.ImplSettingsRepository.Companion.DEFAULT_CODE
import ru.alkarps.android.school2021.hw18.domen.model.Language
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.SettingsViewModel
import ru.alkarps.android.school2021.hw18.presentation.model.CountryView
import ru.alkarps.android.school2021.hw18.presentation.model.DivisionView

/**
 * Фрагмент для работы с настройками.
 * При изменении страны - обновляет список доступных территориальных подразделений
 */
class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
    private lateinit var countries: List<CountryView>
    private lateinit var subdivisionPreference: ListPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val activity = requireActivity()
        val factory = (activity.applicationContext as HolidayApiApplication)
            .holidayMain(activity)
            .settingsViewModelFactory()
        val viewModel = ViewModelProvider(activity, factory).get(SettingsViewModel::class.java)
        countries = viewModel.countriesLiveData.value ?: emptyList()
        initListPreference(viewModel.languagesLiveData.value ?: emptyList())
        val countriesPreference =
            initListPreference(CURRENT_COUNTRY_KEY, countries.map { it.country })
        countriesPreference.onPreferenceChangeListener = this
        val subdivisions = getSubdivisionByCountryCode(countriesPreference.value ?: DEFAULT_CODE)
        subdivisionPreference = initListPreference(CURRENT_SUBDIVISION_KEY, subdivisions)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean =
        if (preference.key in arrayOf(
                CURRENT_COUNTRY_KEY,
                CURRENT_LANGUAGE_KEY,
                CURRENT_SUBDIVISION_KEY
            )
        ) true else super.onPreferenceTreeClick(preference)

    private fun getSubdivisionByCountryCode(code: String) =
        countries.filter { it.country.code == code }.flatMap { it.divisions }

    private fun initListPreference(languages: List<Language>) =
        findPreference<ListPreference>(CURRENT_LANGUAGE_KEY)?.apply {
            entries = languages.map { it.name }.toTypedArray()
            entryValues = languages.map { it.code }.toTypedArray()
            setDefaultValue(DEFAULT_CODE)
        } ?: throw createIllegalStateException(CURRENT_LANGUAGE_KEY)

    private fun createIllegalStateException(key: String) = IllegalStateException("$key not found")

    private fun initListPreference(key: String, divisionViews: List<DivisionView>) =
        findPreference<ListPreference>(key)?.apply {
            setEntryForListPreference(this, divisionViews)
            setDefaultValue(DEFAULT_CODE)
        } ?: throw createIllegalStateException(key)

    private fun setEntryForListPreference(p: ListPreference, divisionViews: List<DivisionView>) {
        p.apply {
            entries = divisionViews.map { it.name }.toTypedArray()
            entryValues = divisionViews.map { it.code }.toTypedArray()
        }
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        Log.w(TAG, "${preference?.key} $newValue")
        if (preference != null && preference.key == CURRENT_COUNTRY_KEY) {
            val subdivisions = getSubdivisionByCountryCode(newValue.toString())
            setEntryForListPreference(subdivisionPreference, subdivisions)
            subdivisionPreference.value = subdivisions.last().code
        }
        return true
    }

    companion object {
        private const val TAG = "SettingsFragment"
    }
}