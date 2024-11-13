package com.nosh.food.ui.cook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nosh.food.databinding.ItemRecommendatationBinding
import com.nosh.food.domain.model.RecommendationUI

class RecommendationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var recommendataions: List<RecommendationUI>? = null

    fun setList(list: List<RecommendationUI>?) {
        recommendataions = null
        recommendataions = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecommendationVH(
            ItemRecommendatationBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return recommendataions?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        recommendataions?.get(position)?.let {
            (holder as RecommendationVH).bindUi(it)
        }
    }
}

class RecommendationVH(private val binding: ItemRecommendatationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindUi(item: RecommendationUI) {
        binding.name.text = item.name
        Glide.with(binding.itemImage)
            .load(item.image)
            .into(binding.itemImage)
    }
}