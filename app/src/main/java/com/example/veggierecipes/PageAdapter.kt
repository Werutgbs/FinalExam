package com.example.veggierecipes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(
    fragmentManager: FragmentManager, private val recipe: Recipe
) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                IngredientsFragment(recipe)

            }
            else -> {
                InstructionsFragment(recipe)

            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Ingredients"
            else -> "Instructions"
        }
    }
}