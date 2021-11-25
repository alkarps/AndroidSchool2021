package ru.alkarps.android.school2021.hw18.presentation.activity.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.presentation.activity.day.HolidaysDayActivity
import ru.alkarps.android.school2021.hw18.presentation.activity.day.HolidaysDayActivity.Companion.DAY_WITH_HOLIDAYS_KEY
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView

/**
 * Фрагмент для отображения количества праздников на текущий день, а так же переход к ним
 */
class MainCountHolidaysFragment : Fragment(R.layout.main_count_holidays_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getParcelable<DayWithHolidaysView>(DAY_WITH_HOLIDAYS_KEY)?.apply {
            view.findViewById<MaterialTextView>(R.id.count_holidays_fragment_label).text =
                "Количество праздников сегодня: ${this.holidays.size}"
            if (this.holidays.isNotEmpty()) {
                val button = view.findViewById<MaterialButton>(R.id.show_holidays_at_this_day)
                button.visibility = View.VISIBLE
                button.findViewById<MaterialButton>(R.id.show_holidays_at_this_day)
                    .setOnClickListener {
                        val intent =
                            Intent(requireActivity(), HolidaysDayActivity::class.java).apply {
                                putExtra(DAY_WITH_HOLIDAYS_KEY, this)
                            }
                        startActivity(intent)
                    }
            }
        }
    }

    companion object {
        /**
         * Метод создания фрагмента
         *
         * @param dayWithHolidaysView описания дня с праздниками
         * @return новый инстанс [MainCountHolidaysFragment]
         */
        fun create(dayWithHolidaysView: DayWithHolidaysView): MainCountHolidaysFragment =
            MainCountHolidaysFragment().apply {
                arguments = bundleOf(DAY_WITH_HOLIDAYS_KEY to dayWithHolidaysView)
            }
    }
}