package ru.alkarps.android.school2021.hw18.presentation.activity.day

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.HolidaysDayActivityBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.day.adapter.HolidaysDayAdapter
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView

/**
 * Activity для оборажения праздников в выбранный день
 *
 * @constructor Новый экземпляр активити
 */
class HolidaysDayActivity : AppCompatActivity() {
    private lateinit var binding: HolidaysDayActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HolidaysDayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)?.apply {
            divider.setDrawable(this)
        }
        binding.holidays.addItemDecoration(divider)

        intent.getParcelableExtra<DayWithHolidaysView>(DAY_WITH_HOLIDAYS_KEY)?.apply {
            binding.holidays.adapter = HolidaysDayAdapter(this.holidays)
            supportActionBar?.title = this.date
        }
    }

    companion object {
        const val DAY_WITH_HOLIDAYS_KEY = "DayWithHolidays"
        const val SELECTED_DATE_KEY = "DayWithHolidays"
    }
}