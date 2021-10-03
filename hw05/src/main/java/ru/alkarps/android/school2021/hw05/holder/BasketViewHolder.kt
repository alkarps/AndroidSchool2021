package ru.alkarps.android.school2021.hw05.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw05.model.BasketItem

abstract class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun onBind(item:BasketItem)
}