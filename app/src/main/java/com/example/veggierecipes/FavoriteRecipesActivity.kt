package com.example.veggierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class FavoriteRecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_recipes)

        supportActionBar?.title = "Favorites"
        val recyclerViewFavoriteRecipes = findViewById<RecyclerView>(R.id.recyclerview_favoriterecipes)

        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(RecipeItem())

        recyclerViewFavoriteRecipes.adapter = adapter

    }
}