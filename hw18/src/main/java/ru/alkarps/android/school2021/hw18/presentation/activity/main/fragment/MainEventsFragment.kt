package ru.alkarps.android.school2021.hw18.presentation.activity.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.presentation.activity.event.EventCreateActivity
import ru.alkarps.android.school2021.hw18.presentation.activity.main.adapter.EventsAdapter
import ru.alkarps.android.school2021.hw18.presentation.model.EventView

/**
 * Фрагмент для отобраения событий на выбранный день
 */
class MainEventsFragment : Fragment(R.layout.main_events_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val events = requireArguments().getParcelableArrayList(EVENTS_KEY) ?: emptyList<EventView>()
        val recycler = view.findViewById<RecyclerView>(R.id.events_fragment_recycler)
        recycler.adapter = EventsAdapter(events)
        val context = requireContext()
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, context.theme)?.apply {
            divider.setDrawable(this)
        }
        recycler.addItemDecoration(divider)
        view.findViewById<FloatingActionButton>(R.id.add_new_event).setOnClickListener {
            startActivity(Intent(requireActivity(), EventCreateActivity::class.java))
        }
    }

    companion object {
        private const val EVENTS_KEY = "MAIN_EVENTS_KEY"

        /**
         * Метод создания фрагмента для отображения событий на выбранный день
         *
         * @param events список событий для отображения
         * @return новый инстанс [MainEventsFragment]
         */
        fun create(events: List<EventView>): MainEventsFragment = MainEventsFragment().apply {
            arguments = bundleOf(EVENTS_KEY to ArrayList(events))
        }
    }
}