package ru.alkarps.android.school2021.hw05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.alkarps.android.school2021.hw05.model.Basket

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private val baskets = mutableListOf<Basket>(Basket())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_item_layout, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket = baskets[position]
        holder.button.setOnClickListener {
            basket.addApple()
            holder.image.setImageLevel(basket.getImageLevel())
        }
    }

    override fun getItemCount(): Int = baskets.size

    fun addBasket() {
        baskets.add(Basket())
    }

    fun removeAllBaskets() = baskets.clear()

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.basket_image)
        val button: Button = itemView.findViewById(R.id.add_apple)
    }
}