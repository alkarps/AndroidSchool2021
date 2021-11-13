package ru.alkarps.android.school2021.hw29.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw29.R
import ru.alkarps.android.school2021.hw29.databinding.DictionaryItemBinding
import ru.alkarps.android.school2021.hw29.domen.models.DictionaryItem

class DictionaryAdapter(
    private val items: List<DictionaryItem>
) : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder =
        DictionaryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.dictionary_item, parent, false)
        )

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class DictionaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DictionaryItemBinding.bind(itemView)
        fun onBind(di: DictionaryItem) {
            binding.keyword.text = di.keyword
            binding.translation.text = di.translation
            binding.wordImage.visibility = GONE
        }
    }
}