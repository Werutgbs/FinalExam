package com.example.veggierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailedRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_recipe)


        val recipe = intent.getSerializableExtra("recipe") as? Recipe
        val tabNames = listOf<String>("Ingredients","Instructions")
        if (recipe != null) {
            Glide.with(this).load(recipe.imageUrl)
                .into(findViewById(R.id.recipe_detailed_background))
            supportActionBar?.title = recipe.name
        }


        val pageAdapter = recipe?.let { PageAdapter(this, it) }
        findViewById<ViewPager2>(R.id.view_pager).adapter = pageAdapter
//        findViewById<TabLayout>(R.id.tab_layout).setupWithViewPager(findViewById(R.id.view_pager))
        TabLayoutMediator(
            findViewById<TabLayout>(R.id.tab_layout),
            findViewById(R.id.view_pager)
        ) { tab, position -> tab.text = (tabNames[position]) }.attach()
//       TODO add credits
    }


}