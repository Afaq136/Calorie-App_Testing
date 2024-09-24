package com.example.calorieapp

import Food
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide

class FoodAdapter(
    private val foodList: List<Food>,
    private val onFoodClickListener: OnFoodClickListener
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodName: TextView = view.findViewById(R.id.name_textView)
        val calories: TextView = view.findViewById(R.id.calories_textView)
        val saturatedFat: TextView = view.findViewById(R.id.fatSaturated_textView)
        val protein: TextView = view.findViewById(R.id.protein_TextView)
        val carbohydrate: TextView = view.findViewById(R.id.carbohydrate_textView)
        val fiber: TextView = view.findViewById(R.id.fiber_textView)

        init {
            // Set click listener for the item
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onFoodClickListener.onFoodClick(foodList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food: Food = foodList[position]

        holder.foodName.text = food.getName()
        holder.calories.text = food.getCalories().toString()
        holder.saturatedFat.text = food.getFatSaturated().toString()
        holder.protein.text = food.getProtein().toString()
        holder.carbohydrate.text = food.getCarbohydrate().toString()
        holder.fiber.text = food.getFiber().toString()
    }

    // Interface for handling item clicks
    interface OnFoodClickListener {
        fun onFoodClick(food: Food)
    }
}