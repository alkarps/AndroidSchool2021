package ru.alkarps.android.school2021.hw14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import ru.alkarps.android.school2021.hw14.fragment.CounterFragment
import ru.alkarps.android.school2021.hw14.fragment.DisplayFragment

class MainActivity : AppCompatActivity(), OperationApi {
    private lateinit var counter: CounterFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counter = supportFragmentManager.findFragmentById(R.id.counter_fragment) as CounterFragment
        supportFragmentManager.addOnBackStackChangedListener {
            counter.updateCounter(supportFragmentManager.backStackEntryCount)
        }
    }

    override fun addTransaction(addOperation: Boolean, needAddToBackstack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            val displayFragment = DisplayFragment.newInstance(addOperation)
            if (addOperation) add(R.id.display_fragment, displayFragment)
            else replace(R.id.display_fragment, displayFragment)
            if (needAddToBackstack) addToBackStack(BACK_STACK_NAME)
            setTransition(TRANSIT_FRAGMENT_OPEN)
        }.commit()
    }

    override fun removeTransaction() {
        supportFragmentManager.popBackStackImmediate()
    }

    companion object {
        const val BACK_STACK_NAME = "BackStackName"
    }
}