package ru.alkarps.android.school2021.hw18.presentation.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.alkarps.android.school2021.hw18.HolidayApiApplication
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.MainActivityBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.main.fragment.MainCountHolidaysFragment
import ru.alkarps.android.school2021.hw18.presentation.activity.main.fragment.MainEventsFragment
import ru.alkarps.android.school2021.hw18.presentation.activity.main.view.model.MainViewModel
import ru.alkarps.android.school2021.hw18.presentation.activity.settings.SettingsActivity
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.util.asString
import java.util.*

/**
 * Активити главного экрана
 *
 * @constructor Новый экземпляр активити главного экрана
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var selectedDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedDate = Calendar.getInstance()
        binding.currentDateLabel.text = selectedDate.asString()
        binding.currentDateLabel.setOnClickListener { changeCurrentDate() }

        initViewModel()

        if (savedInstanceState != null) getDateFromSavedInstanceState(savedInstanceState)
    }

    private fun changeCurrentDate() {
        val dataPicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Выберите дату")
            .setSelection(selectedDate.timeInMillis)
            .build()
        dataPicker.addOnPositiveButtonClickListener {
            changeSelectedDate(it - TimeZone.getDefault().getOffset(Date().time))
            viewModel.loadDataByDate(selectedDate)
        }
        dataPicker.show(supportFragmentManager, dataPicker.toString())
    }

    private fun changeSelectedDate(time: Long) {
        selectedDate.timeInMillis = time
        binding.currentDateLabel.text = selectedDate.asString()
    }

    private fun initViewModel() {
        val factory = (applicationContext as HolidayApiApplication)
            .holidayMain(this)
            .mainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.progress.observe(this, this::progressObserve)
        viewModel.holidays.observe(this, this::showHoliday)
        viewModel.events.observe(this, this::showEvents)
        viewModel.errorMessages.observe(this) {
            Snackbar.make(binding.root, it, BaseTransientBottomBar.LENGTH_LONG).show()
        }
    }

    private fun progressObserve(isFinish: Boolean) {
        val visibility = if (isFinish) View.GONE to View.VISIBLE else View.VISIBLE to View.GONE
        binding.progressFrameLayout.visibility = visibility.first
        binding.currentDateLabel.visibility = visibility.second
        binding.countHolidaysFragment.visibility = visibility.second
        binding.eventsFragment.visibility = visibility.second
    }

    private fun showHoliday(days: List<DayWithHolidaysView>) {
        supportFragmentManager.beginTransaction().replace(
            R.id.count_holidays_fragment, MainCountHolidaysFragment.create(days.firstOrNull())
        ).commit()
    }

    private fun showEvents(events: List<EventView>) {
        supportFragmentManager.beginTransaction().replace(
            R.id.events_fragment, MainEventsFragment.create(events)
        ).commit()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadDataByDate(selectedDate)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.setting_menu) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(SELECTED_DATE_KEY, selectedDate.timeInMillis)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        getDateFromSavedInstanceState(savedInstanceState)
    }

    private fun getDateFromSavedInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey(SELECTED_DATE_KEY)) {
            changeSelectedDate(savedInstanceState.getLong(SELECTED_DATE_KEY))
        }
    }

    companion object {
        private const val SELECTED_DATE_KEY = "SelectedDateKey"
    }
}