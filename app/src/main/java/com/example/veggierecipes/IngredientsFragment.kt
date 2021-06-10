package com.example.veggierecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class IngredientsFragment(private val recipe: Recipe) : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ingredientsAdapter = GroupAdapter<ViewHolder>()
        for (ingredient:String in recipe.ingredients!!){
            ingredientsAdapter.add(IngredientItem(ingredient))
        }

        view.findViewById<RecyclerView>(R.id.recyclerview_ingredients).adapter = ingredientsAdapter


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_ingredients, container, false)

    }


}