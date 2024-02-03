package com.ayan.wiseapp.services

import com.ayan.wiseapp.models.Drinks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {
    @GET("search.php")
    suspend fun searchCocktailByName(@Query("s") name: String): Response<Drinks>
}