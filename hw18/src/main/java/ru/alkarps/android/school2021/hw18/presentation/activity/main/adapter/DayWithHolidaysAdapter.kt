package ru.alkarps.android.school2021.hw18.presentation.activity.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.DayWithHolidaysItemBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.day.HolidaysDayActivity
import ru.alkarps.android.school2021.hw18.presentation.activity.day.HolidaysDayActivity.Companion.DAY_WITH_HOLIDAYS_KEY
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView

/**
 * Адаптер для отображения дней с праздниками
 *
 * @property daysWithHolidays дни с праздниками
 * @constructor Новый экземпляр адаптера
 */
class DayWithHolidaysAdapter(
    private val daysWithHolidays: List<DayWithHolidaysView>
) : RecyclerView.Adapter<DayWithHolidaysAdapter.DyaWithHolidaysViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DyaWithHolidaysViewHolder {
        return DyaWithHolidaysViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.day_with_holidays_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DyaWithHolidaysViewHolder, position: Int) {
        holder.onBind(daysWithHolidays[position])
    }

    override fun getItemCount(): Int = daysWithHolidays.size

    class DyaWithHolidaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DayWithHolidaysItemBinding.bind(itemView)
        private val context = itemView.context

        fun onBind(dayWithHolidaysView: DayWithHolidaysView) {
            binding.dayWithHolidays.text = dayWithHolidaysView.date
            binding.dayWithHolidays.setOnClickListener {
                val intent = Intent(context, HolidaysDayActivity::class.java).apply {
                    putExtra(DAY_WITH_HOLIDAYS_KEY, dayWithHolidaysView)
                }
                context.startActivity(intent)
            }
        }
    }
}