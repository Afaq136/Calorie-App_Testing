package com.example.calorieapp

import Food
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
//import com.example.calorieapp.FoodAdapter
//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicHeader
import okhttp3.Headers

// MainActivity.kt
class MainActivity : AppCompatActivity(), FoodFragment.OnButtonClickListener {

    private lateinit var foodList: MutableList<Food>
    private var calorieGoal: Double = 0.0
    private lateinit var userInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodList = mutableListOf<Food>()

        val searchButton = findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener {
            val calorieGoalEditText = findViewById<EditText>(R.id.calorie_goal_answer)

            userInput = findViewById<EditText>(R.id.food_answer).text.toString()

            if (calorieGoalEditText.text.toString().isNotEmpty() && userInput.isNotEmpty()) {
                convertEditTextToDouble(calorieGoalEditText)
                getFoodInfo(userInput)
            } else {
                // Display a message or perform some action if either EditText is empty
                Toast.makeText(this, "Please enter values in both fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun convertEditTextToDouble(calorieGoalEditText: EditText?) {
        // Get the text from the EditText
        val calorieGoalText = calorieGoalEditText?.text.toString().trim()

        // Convert the text to a Double
        calorieGoal = try {
            calorieGoalText.toDouble()
        } catch (e: NumberFormatException) {
            // Handle the case where the input is not a valid Double
            // For now, let's set it to 0.0 as a default value.
            0.0
        }
    }

    private fun getFoodInfo(food: String) {
        val client = AsyncHttpClient()
        val API_KEY = "QpWHeJe+v61Szjf1tIYvPg==edPDwj9M04faGItV"
        val url = "https://api.api-ninjas.com/v1/nutrition?query=${food}"

        val params = RequestParams()
        val requestHeaders = RequestHeaders()
        requestHeaders["x-api-key"] = API_KEY

        client[url, requestHeaders, params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val foodArray = json.jsonArray

                // Create a new instance of FoodFragment
                val foodFragment = FoodFragment.newInstance(foodArray.toString(), "param2")

                // Replace the current fragment with the FoodFragment
                supportFragmentManager.beginTransaction().apply {
                    foodFragment.setOnButtonClickListener(this@MainActivity)
                    replace(R.id.flFragment, foodFragment)
                    addToBackStack(null) // This allows the user to navigate back to the previous fragment
                    commit()
                }

                foodList = Food.fromJSONArray(foodArray)

                var countCalories = 0.0
                for (food in foodList) {
                    val caloriesValue = food.getCalories()
                    countCalories += caloriesValue
                }

                // Check if the calorie goal is reached
                if (countCalories >= calorieGoal) {
                    // Display the RecyclerViewFragment when the goal is reached
                    displayRecyclerViewFragment(foodList)
                }

                Log.d("Food", "response successful$json")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Food Error", errorResponse)
            }
        }]
    }

    // checks if the calorie goal is reached after fetching food information,
    // and if so, it displays the RecyclerViewFragment with the selected foods.
    // If the goal is not reached, it continues with the original flow by
    // displaying the FoodFragment.
    private fun displayRecyclerViewFragment(selectedFoods: List<Food>) {
        val recyclerViewFragment = RecyclerViewFragment.newInstance(selectedFoods)

        // Replace the current fragment with the RecyclerViewFragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, recyclerViewFragment)
            addToBackStack(null)
            commit()
        }
    }

    // Implement the button click methods from the interface
    override fun onYesButtonClick() {
        // Handle Yes button click (navigate back to the main activity page)
        supportFragmentManager.popBackStack()
    }

    override fun onNoButtonClick() {
        // Handle No button click (navigate back to the main activity page)
        supportFragmentManager.popBackStack()
    }
}
