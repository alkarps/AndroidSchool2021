package ru.alkarps.android.school2021.hw05

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alkarps.android.school2021.hw05.holder.BasketViewHolder
import ru.alkarps.android.school2021.hw05.holder.ViewType
import ru.alkarps.android.school2021.hw05.model.BasketItem

class BasketAdapter : ListAdapter<BasketItem, BasketViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getTypeId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return ViewType.getTypeById(viewType).initHolder(parent)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<BasketItem>() {
        override fun areItemsTheSame(old: BasketItem, new: BasketItem) = old == new

        override fun areContentsTheSame(old: BasketItem, new: BasketItem) = old == new
    }
}