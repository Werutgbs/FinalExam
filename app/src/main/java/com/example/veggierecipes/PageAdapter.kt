package com.example.veggierecipes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(
    detailedRecipeActivity: DetailedRecipeActivity, private val recipe: Recipe
) : FragmentStateAdapter(detailedRecipeActivity) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                IngredientsFragment(recipe)

            }
            else -> {
                InstructionsFragment(recipe)

            }
        }
    }
}