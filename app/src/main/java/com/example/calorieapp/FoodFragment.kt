package com.example.calorieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
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

        // Your existing code...

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
