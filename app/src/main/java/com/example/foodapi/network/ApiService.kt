package com.example.foodapi.network

import com.example.foodapi.model.FoodData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/recipes/complexSearch")
    fun getFoods(
        @Query("apiKey") apiKey: String = "4033fe66e0ab4f31b72846fe85317235"
    ): Call<FoodData>

    @GET("/recipes/complexSearch")
    fun searchFoods(
        @Query("apiKey") apiKey: String = "4033fe66e0ab4f31b72846fe85317235",
        @Query("query") query: String
    ): Call<FoodData>


}