package com.example.calorieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private val ARG_PARAM1 = hashMapOf<String, String>()
private val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodFragment : Fragment() {

    private var buttonClickListener: OnButtonClickListener? = null

    interface OnButtonClickListener {
        fun onYesButtonClick()
        fun onNoButtonClick()
    }

    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        this.buttonClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)


        var name_ = "apple"
        var calories_ = "83"
        var fat_saturated_ = "0.0"
        var protein_ = "0.0"
        var carbohydrates_ = "0.0"
        var fiber_ = "0.0"


        //testing
        val jsonArrayString = arguments?.getString(ARG_PARAM1)
        val jsonArray = JSONArray(jsonArrayString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.optJSONObject(i)
            name_ = jsonObject?.optString("name", "").toString()
            calories_ = jsonObject?.optString("calories", "").toString()
            fat_saturated_ = jsonObject?.optString("fat_total_g", "").toString()
            protein_ = jsonObject?.optString("protein_g", "").toString()
            carbohydrates_ = jsonObject?.optString("carbohydrates_total_g", "").toString()
            fiber_ = jsonObject?.optString("fiber_g", "").toString()

        }

        // Assuming that your JSON array contains only one object, you can get the first one


        // Your existing code...
        val name = view.findViewById<TextView>(R.id.name_textView)
        val calories = view.findViewById<TextView>(R.id.calories_textView)
        val fatSaturated = view.findViewById<TextView>(R.id.fatSaturated_textView)
        val protein = view.findViewById<TextView>(R.id.protein_TextView)
        val carbohydrate = view.findViewById<TextView>(R.id.carbohydrate_textView)
        val fiber = view.findViewById<TextView>(R.id.fiber_textView)

        name.text = name_
        calories.text = calories_
        fatSaturated.text = fat_saturated_
        protein.text = protein_
        carbohydrate.text = carbohydrates_
        fiber.text = fiber_


        // Assuming you have buttons with ids "yes_button" and "no_button"
        val yesButton: Button = view.findViewById(R.id.yes_button)
        val noButton: Button = view.findViewById(R.id.no_button)

        // Set click listeners for the buttons
        yesButton.setOnClickListener {
            buttonClickListener?.onYesButtonClick()
        }

        noButton.setOnClickListener {
            buttonClickListener?.onNoButtonClick()
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
