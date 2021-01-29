package com.example.veggierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detailed_recipe.*
import kotlinx.android.synthetic.main.fragment_ingredients.*

class DetailedRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_recipe)

        val recipe = intent.getSerializableExtra("recipe") as? Recipe
        if (recipe != null) {
            Glide.with(this).load(recipe.imageUrl).into(findViewById(R.id.recipe_detailed_background))
            supportActionBar?.title = recipe.name
        }





        val pageAdapter = recipe?.let { PageAdapter(supportFragmentManager, it) }
        findViewById<ViewPager>(R.id.view_pager).adapter = pageAdapter
        findViewById<TabLayout>(R.id.tab_layout).setupWithViewPager(findViewById(R.id.view_pager))
    }

}