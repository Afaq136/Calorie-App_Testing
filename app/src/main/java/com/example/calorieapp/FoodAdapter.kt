package com.example.calorieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide

class FoodAdapter(val foodList: MutableList<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder (view: View) : RecyclerView.ViewHolder(view){

        // val foodImageView: ImageView
        val foodName: TextView
        val calories : TextView
        val saturatedFat : TextView
        val protein : TextView
        val carbohydrate : TextView
        val fiber : TextView

        // set the foodImageView value in the init body of the foodViewHolder class
        // the code inside the init body will always run when the class is instantiated
        init {
            // foodImageView = view.findViewById(R.id.gameOfThrone_image)
            foodName = view.findViewById(R.id.name_textView)
            calories = view.findViewById(R.id.calories_textView)
            saturatedFat = view.findViewById(R.id.fatSaturated_textView)
            protein = view.findViewById(R.id.protein_TextView)
            carbohydrate = view.findViewById(R.id.carbohydrate_textView)
            fiber = view.findViewById(R.id.fiber_textView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        val food : Food
        food = foodList.get(position)

        holder.foodName.setText(food.getName())
        holder.calories.setText(food.getCalorie().toString())
        holder.saturatedFat.setText(food.getFatSaturated().toString())
        holder.protein.setText(food.getProtein().toString())
        holder.carbohydrate.setText(food.getCarbohydrate().toString())
        holder.fiber.setText(food.getFiber().toString())


//        Glide.with(holder.itemView)
//            .load(food.getCharacterImageURL()) // foodList contains a list of URLs to the character photos
//            .centerCrop()
//            .into(holder.foodImageView) // in this method basically you're passing the imageView that you want to put this photo URL into

    }
}