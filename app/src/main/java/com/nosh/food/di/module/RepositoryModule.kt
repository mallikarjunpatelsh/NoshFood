package com.nosh.food.di.module

import com.nosh.food.data.repository.ApiRepository
import com.nosh.food.di.qualifier.Repository
import com.nosh.food.domain.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    @Repository
    fun bindsWeatherRepository(repo: ApiRepository): IRepository
}