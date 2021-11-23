package ru.alkarps.android.school2021.hw18.presentation.activity.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.alkarps.android.school2021.hw18.HolidayApiApplication
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.SettingsActivityBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.view.model.SettingsViewModel

/**
 * Активити для отображения настроек.
 *
 * Во время подгрузки справочных данных - должен отображаться прогресс бар загрузки
 *
 */
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = (applicationContext as HolidayApiApplication)
            .holidayMain(this)
            .settingsViewModelFactory()
        val viewModel = ViewModelProvider(this, factory).get(SettingsViewModel::class.java)
        viewModel.progressLiveData.observe(this) {
            binding.settingLoadProgress.visibility = if (it) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.setting_fragment, SettingsFragment())
                    .commit()
                View.GONE
            } else View.VISIBLE
        }
        if (savedInstanceState == null) {
            viewModel.loadAllData()
        }
    }
}