package com.nosh.food.domain.model

import com.nosh.food.data.service.data.Dishes
import com.nosh.food.data.service.data.DishesItem

data class CookUI(
//    val dishes: ArrayList<DishesItem>,
//    val recommendations: ArrayList<RecommendationUI>,
    val cookItems: List<DataItem>
)

sealed class DataItem {

    data class DishesUI<T>(
        val itemType: Int,
        val items: ArrayList<T>?
    ): DataItem()

}
