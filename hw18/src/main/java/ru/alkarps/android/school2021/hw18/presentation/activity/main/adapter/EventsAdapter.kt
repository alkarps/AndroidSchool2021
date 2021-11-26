package ru.alkarps.android.school2021.hw18.presentation.activity.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw18.R
import ru.alkarps.android.school2021.hw18.databinding.MainEventsFragmentRecycleItemBinding
import ru.alkarps.android.school2021.hw18.presentation.activity.event.EventChangeActivity
import ru.alkarps.android.school2021.hw18.presentation.model.EventView

/**
 * Адаптер для событий на главном экране
 *
 * @property events доступные события навыбранный день
 */
class EventsAdapter(
    private val events: List<EventView>
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_events_fragment_recycle_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.onBind(events[position])
    }

    override fun getItemCount(): Int = events.size

    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = MainEventsFragmentRecycleItemBinding.bind(itemView)
        private val context = itemView.context

        fun onBind(event: EventView) {
            binding.eventTime.text = event.startTime
            binding.eventName.text = event.name
            binding.eventsFragmentRecyclerItem.setOnClickListener {
                val intent = Intent(context, EventChangeActivity::class.java).apply {
                    putExtra(EventChangeActivity.EVENT_CHANGE_KEY, event)
                }
                context.startActivity(intent)
            }
        }
    }
}