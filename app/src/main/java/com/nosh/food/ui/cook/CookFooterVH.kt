package com.nosh.food.ui.cook

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nosh.food.databinding.ItemFooterBinding

class CookFooterVH(private val binding: ItemFooterBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        Glide.with(binding.ivExplore)
            .load("https://www.tastingtable.com/img/gallery/20-delicious-indian-dishes-you-have-to-try-at-least-once/intro-1645057933.jpg")
            .into(binding.ivExplore)
    }
}