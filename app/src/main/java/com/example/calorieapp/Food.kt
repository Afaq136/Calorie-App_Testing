package com.example.calorieapp

import android.os.Bundle
import android.widget.Button
import org.json.JSONArray
import org.json.JSONObject
open class Food {

    private lateinit var name: String
    private var calories: Double = 0.0
    private var fatSaturated: Double = 0.0
    private var protein: Double = 0.0
    private var carbohydrate: Double = 0.0
    private var fiber: Double = 0.0

    constructor(jsonObject: JSONObject) {
        name = jsonObject.getString("name")
        calories = jsonObject.getDouble("calories")
        fatSaturated = jsonObject.getDouble("fat_saturated_g")
        protein = jsonObject.getDouble("protein_g")
        carbohydrate = jsonObject.getDouble("carbohydrates_total_g")
        fiber = jsonObject.getDouble("fiber_g")
    }

    companion object {


        @JvmStatic
        fun fromJSONArray(foodJSONArray: JSONArray): MutableList<Food> {
            val foodList: MutableList<Food> = mutableListOf()

            val calorieCount = 0

            for (i in 0 until foodJSONArray.length()) {



                foodList.add(object :
                    Food(foodJSONArray.getJSONObject(i)) {
                })


            }
            return foodList
        }
    }

//    fun getfoodImageURL(): String {
//        return characterImage
//    }

    fun getName(): String {
        return name
    }

    fun getCalorie(): Double {
        return calories
    }

    fun getFatSaturated(): Double {
        return fatSaturated
    }

    fun getProtein(): Double {
        return protein
    }
    fun getCarbohydrate(): Double {
        return carbohydrate
    }

    fun getFiber(): Double {
        return fiber
    }
}