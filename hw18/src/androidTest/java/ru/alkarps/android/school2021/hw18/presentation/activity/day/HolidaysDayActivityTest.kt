package ru.alkarps.android.school2021.hw18.presentation.activity.day

import android.content.Intent
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.matcher.RecyclerViewMatcher
import ru.alkarps.android.school2021.hw18.presentation.activity.day.adapter.HolidaysDayAdapter
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.model.HolidayView

@RunWith(AndroidJUnit4::class)
class HolidaysDayActivityTest {
    private val dayWithHolidaysView = DayWithHolidaysView(
        "date 1", listOf(
            HolidayView("1", "Name 1", "Date 1", "Observed 1", false),
            HolidayView("2", "Name 2", "Date 2", "Observed 2", true)
        )
    )

    @get:Rule
    var activityScenarioRule = activityScenarioRule<HolidaysDayActivity>(intent = Intent(
        getApplicationContext(), HolidaysDayActivity::class.java
    ).apply {
        putExtra(
            HolidaysDayActivity.DAY_WITH_HOLIDAYS_KEY, dayWithHolidaysView
        )
    })

    @Test
    fun displayView() {
        for ((ind, item) in dayWithHolidaysView.holidays.withIndex()) {
            onView(withId(R.id.holidays)).perform(
                actionOnItemAtPosition<HolidaysDayAdapter.HolidayViewHolder>(ind, click())
            )
            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_name))
                .check(matches(withText(item.name)))

            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_public))
                .check(matches(withEffectiveVisibility(if (item.public) Visibility.VISIBLE else Visibility.GONE)))
            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_date))
                .check(matches(withText(item.date)))
            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_observed))
                .check(matches(withText(item.observed)))
        }
    }
}