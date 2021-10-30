package ru.alkarps.android.school2021.hw18.presentation.activity.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.DayWithHolidaysItemBinding
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView

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

        fun onBind(dayWithHolidaysView: DayWithHolidaysView) {
            binding.dayWithHolidays.text = dayWithHolidaysView.date
        }
    }
}