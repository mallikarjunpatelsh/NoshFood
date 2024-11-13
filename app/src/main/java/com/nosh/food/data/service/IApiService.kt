package com.nosh.food.data.service

import com.nosh.food.data.service.data.Dishes
import retrofit2.Response
import retrofit2.http.GET

interface IApiService {
    @GET(AppConstants.URLConstants.API_VERSION_V1 + AppConstants.URLConstants.SLASH + AppConstants.URLConstants.SEARCH_ENDPOINT)
    suspend fun getDishes(): Response<Dishes>
}