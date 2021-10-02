package ru.alkarps.android.school2021.hw05

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private val baskets = mutableListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun addBasket(){

    }

    fun removeAllBaskets() = baskets.clear()

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}