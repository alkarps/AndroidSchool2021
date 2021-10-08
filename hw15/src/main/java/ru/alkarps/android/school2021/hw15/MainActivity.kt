package ru.alkarps.android.school2021.hw15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.alkarps.android.school2021.hw15.timer.TimerDisplayFragment
import ru.alkarps.android.school2021.hw15.timer.TimerSetUpFragment

class MainActivity : AppCompatActivity(), TimerApi {
    private val setUpFragment = TimerSetUpFragment.newInstance()
    private val displayFragment = TimerDisplayFragment.newInstance()
    private var timerStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment()
    }

    private fun changeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.timer, if (timerStarted) displayFragment else setUpFragment)
            .commitNow()
    }

    override fun start(startTime: Int) {
        timerStarted = true
        changeFragment()
        displayFragment.updateDisplayedTime(startTime)
    }
}