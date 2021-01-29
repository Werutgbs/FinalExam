package com.example.veggierecipes

import IngredientsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class IngredientsFragment(private val recipe: Recipe) : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ingredientsAdapter = IngredientsAdapter(recipe)

        view.findViewById<RecyclerView>(R.id.recyclerview_ingredients).adapter = ingredientsAdapter

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false)

    }


}