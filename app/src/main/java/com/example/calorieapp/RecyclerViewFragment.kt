package com.example.calorieapp

import Food
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewFragment : Fragment() {

    private lateinit var selectedFoods: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            @Suppress("DEPRECATION")
            val parcelableList = it?.getParcelableArrayList<Food>("selectedFoods") ?: emptyList<Food>()





            if (parcelableList.isNotEmpty()) {
                selectedFoods = parcelableList.toList()
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)

        // Assuming you have a RecyclerView with id "recyclerView" in fragment_recycler_view.xml
        val recyclerView: RecyclerView = view.findViewById(R.id.foodRecyclerView)

        // Set up RecyclerView with the selected foods
        recyclerView.adapter = FoodAdapter(selectedFoods, object : FoodAdapter.OnFoodClickListener {
            override fun onFoodClick(food: Food) {
                // Handle item click, if needed
                // Example: Toast.makeText(requireContext(), "Clicked on ${food.getName()}", Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(selectedFoods: List<Food>) =
            RecyclerViewFragment().apply {
                arguments = bundleOf("selectedFoods" to ArrayList(selectedFoods))
            }
    }
}
