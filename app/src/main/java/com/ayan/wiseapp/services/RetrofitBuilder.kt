package com.ayan.wiseapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: CocktailApiService = retrofit.create(CocktailApiService::class.java)
}