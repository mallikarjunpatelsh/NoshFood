package com.nosh.food.ui.cook

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.nosh.food.data.service.data.DishesItem
import com.nosh.food.databinding.ItemHorizontalRecyclerViewBinding
import com.nosh.food.domain.model.RecommendationUI

class RecyclerViewHorizontalVH(
    private val binding: ItemHorizontalRecyclerViewBinding,
    private val callback: IDishesCallback
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindUi(item: List<*>) {
//        binding.name.text = item.name
//        Glide.with(binding.itemImage)
//            .load(item.image)
//            .into(binding.itemImage)

        /*when (item) {
            is Lis
        }*/
        Log.d("inside", "bindui")
        if (item.first() is RecommendationUI){
            Log.d("inside", "RecommendationUI")
            val adapter = RecommendationAdapter()
            binding.horizontalRv.apply {
                this.adapter = adapter
            }
            adapter.setList(item as List<RecommendationUI>)
            binding.heading.text = "What's on your mind?"
        } else if (item.first() is DishesItem) {
            Log.d("inside", "DishesItem")
            val adapter = DishesAdapter(callback)
            binding.horizontalRv.apply {
                this.adapter = adapter
            }
            adapter.setList(item as List<DishesItem>)
            binding.heading.text = "Recommendations"
            binding.action.text = "Show all"
        }
    }
}