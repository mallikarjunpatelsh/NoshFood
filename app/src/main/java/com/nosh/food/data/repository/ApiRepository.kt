package com.nosh.food.data.repository

import android.content.Context
import com.nosh.food.data.base.BaseRepository
import com.nosh.food.data.base.Resource
import com.nosh.food.data.service.IApiService
import com.nosh.food.domain.IRepository
import com.nosh.food.data.service.data.Dishes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiRepository @Inject constructor(@ApplicationContext private val context: Context, private val apiService: IApiService): BaseRepository(context), IRepository {
    override fun getDishes():Flow<Resource<Dishes>> = getResult{
        apiService.getDishes()
    }
}