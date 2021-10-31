package ru.alkarps.android.school2021.hw18.presentation.activity.day.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.HolidayItemBinding
import ru.alkarps.android.school2021.hw18.presentation.model.HolidayView

/**
 * Адаптер для отображения [HolidayView]
 *
 * @property holidays список праздников
 * @constructor Новый экземпляр адаптера
 */
class HolidaysAdapter(
    private val holidays: List<HolidayView>
) : RecyclerView.Adapter<HolidaysAdapter.HolidayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayViewHolder {
        return HolidayViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.holiday_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        holder.onBind(holidays[position])
    }

    override fun getItemCount(): Int = holidays.size

    class HolidayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HolidayItemBinding.bind(itemView)

        fun onBind(holidayView: HolidayView) {
            binding.holidayName.text = holidayView.name
            binding.holidayDate.text = holidayView.date
            binding.holidayObserved.text = holidayView.observed
            binding.holidayPublic.text = if (holidayView.public) "Yes" else "No"
        }
    }
}