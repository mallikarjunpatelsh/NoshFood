package com.nosh.food.domain

import com.nosh.food.data.base.Resource
import com.nosh.food.data.service.data.Dishes
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getDishes():Flow<Resource<Dishes>>

}