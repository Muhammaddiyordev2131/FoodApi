package com.example.foodapi.model

import com.google.gson.annotations.SerializedName

data class FoodData(
     @SerializedName("results")
     val foodList: List<Food>
 )
data class Food(
    @SerializedName("id")
    val foodId: Int,
    val title: String,
    val image: String
)