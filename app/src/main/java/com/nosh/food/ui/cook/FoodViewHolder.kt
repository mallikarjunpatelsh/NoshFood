package com.nosh.food.ui.cook

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nosh.food.data.service.data.DishesItem
import com.nosh.food.databinding.ItemFoodBinding

class FoodViewHolder(private val binding: ItemFoodBinding, private  val callback: IDishesCallback): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DishesItem) {
        binding.food = item
        Glide.with(binding.foodImageView)
            .load(item.imageUrl)
            .into(binding.foodImageView)
        binding.parentCardview.setOnClickListener {
            callback.onCLick()
        }
    }
}