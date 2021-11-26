package ru.alkarps.android.school2021.hw18.presentation.activity.event

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import ru.alkarps.android.school2021.hw18.HolidayApiApplication
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.EventChangeActivityBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.event.view.model.EventChangeViewModel
import ru.alkarps.android.school2021.hw18.presentation.model.EventView
import ru.alkarps.android.school2021.hw18.util.asString
import ru.alkarps.android.school2021.hw18.util.toStringTime
import java.util.*

class EventChangeActivity : AppCompatActivity() {
    private lateinit var binding: EventChangeActivityBinding
    private lateinit var viewModel: EventChangeViewModel
    private lateinit var event: EventView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EventChangeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getParcelableExtra<EventView>(EVENT_CHANGE_KEY)?.apply { event = this } ?: finish()
        binding.eventChangeNameInput.setText(event.name)
        event.location?.apply { binding.eventChangeLocationInput.setText(this) }
        initViewModel()
        initDateInput()
        initTimeInput()
        initButton()
        supportActionBar?.title = getString(R.string.event_change_title)
    }

    private fun initViewModel() {
        val factory = (applicationContext as HolidayApiApplication)
            .holidayMain(this)
            .eventChangeViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(EventChangeViewModel::class.java)
        viewModel.success.observe(this, { finish() })
        viewModel.errorMessages.observe(this) {
            Snackbar.make(binding.root, it, BaseTransientBottomBar.LENGTH_LONG).show()
        }
    }

    private fun initDateInput() {
        binding.eventChangeDateInput.setText(event.date)
        binding.eventChangeDateInput.inputType = InputType.TYPE_NULL
        binding.eventChangeDateInput.keyListener = null
        binding.eventChangeDateInput.setOnClickListener { showDataPicker() }
        binding.eventChangeDateInput.setOnFocusChangeListener { _, f -> if (f) showDataPicker() }
    }

    private fun showDataPicker() {
        val dataPicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Дата события")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        dataPicker.addOnPositiveButtonClickListener {
            val cal = Calendar.getInstance().apply { timeInMillis = it }
            binding.eventChangeDateInput.setText(cal.asString())
        }
        dataPicker.show(supportFragmentManager, dataPicker.toString())
    }

    private fun initTimeInput() {
        binding.eventChangeTimeInput.setText(event.startTime)
        binding.eventChangeTimeInput.inputType = InputType.TYPE_NULL
        binding.eventChangeTimeInput.keyListener = null
        binding.eventChangeTimeInput.setOnClickListener { showTimePicker() }
        binding.eventChangeTimeInput.setOnFocusChangeListener { _, f -> if (f) showTimePicker() }
    }

    private fun showTimePicker() {
        val dataPicker = MaterialTimePicker.Builder()
            .setTitleText("Время начала события")
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        dataPicker.addOnPositiveButtonClickListener {
            binding.eventChangeTimeInput.setText("${dataPicker.hour.toStringTime()}:${dataPicker.minute.toStringTime()}")
        }
        dataPicker.show(supportFragmentManager, dataPicker.toString())
    }

    private fun initButton() {
        binding.eventChangeButton.setOnClickListener {
            if (inputsIsValid()) {
                viewModel.update(
                    event.copy(
                        name = binding.eventChangeNameInput.text.toString(),
                        date = binding.eventChangeDateInput.text.toString(),
                        startTime = binding.eventChangeTimeInput.text.toString(),
                        location = binding.eventChangeLocationInput.text?.toString()
                    )
                )
            }
        }
    }

    private fun inputsIsValid(): Boolean {
        val validName = validateField(binding.eventChangeNameInput)
        val validDate = validateField(binding.eventChangeDateInput)
        val validTime = validateField(binding.eventChangeTimeInput)
        return validDate && validName && validTime
    }

    private fun validateField(filed: TextInputEditText): Boolean {
        val invalid = filed.text?.toString().isNullOrBlank()
        if (invalid) filed.error = "Обязательное поле."
        return !invalid
    }

    companion object {
        const val EVENT_CHANGE_KEY = "EventChangeKey"
    }
}