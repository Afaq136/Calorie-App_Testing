package com.example.calorieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText


import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
//import com.example.calorieapp.FoodAdapter
//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicHeader
import okhttp3.Headers

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
            convertEditTextToDouble(calorieGoalEditText)
            println(calorieGoal)

            userInput = findViewById<EditText>(R.id.food_answer).text.toString()
            getFoodInfo(userInput)
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

                Log.d("Food", "response successful$json")

                // Now we need to bind food data (these Foods) to our Adapter
                // Assuming you have a RecyclerView in your FoodFragment layout
                // Uncomment and adjust the following lines based on your actual implementation
                // val foodAdapter = FoodAdapter(foodList)
                // foodRecyclerView.adapter = foodAdapter
                // foodRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                // foodRecyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
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
