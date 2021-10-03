package ru.alkarps.android.school2021.hw05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw05.model.Basket
import ru.alkarps.android.school2021.hw05.model.BasketItem

class BasketAdapter : ListAdapter<BasketItem, BasketAdapter.BasketViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getTypeId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item_layout, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket = getItem(position) as Basket
        holder.button.setOnClickListener {
            basket.addApple()
            holder.image.setImageLevel(basket.getImageLevel())
        }
    }

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.basket_image)
        val button: Button = itemView.findViewById(R.id.add_apple)
    }

    private class DiffCallback : DiffUtil.ItemCallback<BasketItem>() {
        override fun areItemsTheSame(old: BasketItem, new: BasketItem) = old == new

        override fun areContentsTheSame(old: BasketItem, new: BasketItem) = old == new
    }
}