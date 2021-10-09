package ru.alkarps.android.school2021.hw15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.alkarps.android.school2021.hw15.timer.TimerApi
import ru.alkarps.android.school2021.hw15.timer.TimerDisplayFragment
import ru.alkarps.android.school2021.hw15.timer.TimerSetUpFragment

class MainActivity : AppCompatActivity(), TimerApi {
    private val setUpFragment = TimerSetUpFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displaySetUp()
    }

    private fun displaySetUp() {
        displayFragment(setUpFragment)
    }

    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.timer, fragment)
            .commitNow()
    }

    override fun start(displayFragment: TimerDisplayFragment) {
        displayFragment(displayFragment)
    }

    override fun stop() {
        displaySetUp()
    }
}