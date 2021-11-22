package ru.alkarps.android.school2021.hw18.presentation.activity.day

import android.content.Intent
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.matcher.RecyclerViewMatcher
import ru.alkarps.android.school2021.hw18.presentation.activity.day.adapter.HolidaysAdapter
import ru.alkarps.android.school2021.hw18.presentation.model.DayWithHolidaysView
import ru.alkarps.android.school2021.hw18.presentation.model.HolidayView

@RunWith(AndroidJUnit4::class)
class DayActivityTest {
    private val dayWithHolidaysView = DayWithHolidaysView(
        "date 1", listOf(
            HolidayView("1", "Name 1", "Date 1", "Observed 1", false),
            HolidayView("2", "Name 2", "Date 2", "Observed 2", true)
        )
    )

    @get:Rule
    var activityScenarioRule = activityScenarioRule<DayActivity>(intent = Intent(
        getApplicationContext(), DayActivity::class.java
    ).apply {
        putExtra(
            DayActivity.DAY_WITH_HOLIDAYS_KEY, dayWithHolidaysView
        )
    })

    @Test
    fun displayView() {
        onView(withId(R.id.selected_day)).check(
            matches(withText(dayWithHolidaysView.date))
        )
        for ((ind, item) in dayWithHolidaysView.holidays.withIndex()) {
            onView(withId(R.id.holidays)).perform(
                actionOnItemAtPosition<HolidaysAdapter.HolidayViewHolder>(ind, click())
            )
            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_name))
                .check(matches(withText(item.name)))
            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_date))
                .check(matches(withText(item.date)))
            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_observed))
                .check(matches(withText(item.observed)))
            onView(RecyclerViewMatcher(R.id.holidays).atPositionOnView(ind, R.id.holiday_public))
                .check(matches(withText(if (item.public) "Yes" else "No")))
        }
    }
}