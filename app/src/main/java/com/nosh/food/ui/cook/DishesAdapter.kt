package com.nosh.food.ui.cook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nosh.food.data.service.data.DishesItem
import com.nosh.food.databinding.ItemFoodBinding

class DishesAdapter(private val callback: IDishesCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private var dishes: List<DishesItem>? = null
    fun setList(list: List<DishesItem>?) {
        dishes = null
        dishes = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FoodViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            ),
            callback
        )
    }

    override fun getItemCount(): Int {
        return dishes?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dishes?.get(position)?.let {
            (holder as FoodViewHolder).bind(it)
        }
    }
}