package ru.alkarps.android.school2021.hw18.presentation.activity.day

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.DayActivityBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.day.adapter.HolidaysAdapter
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView

/**
 * Activity для оборажения праздников в выбранный день
 *
 * @constructor Новый экземпляр активити
 */
class DayActivity : AppCompatActivity() {
    private lateinit var binding: DayActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, theme)?.apply {
            divider.setDrawable(this)
        }
        binding.holidays.addItemDecoration(divider)

        intent.getParcelableExtra<DayWithHolidaysView>(DAY_WITH_HOLIDAYS_KEY)?.apply {
            binding.selectedDay.text = this.date
            binding.holidays.adapter = HolidaysAdapter(this.holidays)
        }
    }

    companion object {
        const val DAY_WITH_HOLIDAYS_KEY = "DayWithHolidays"
    }
}