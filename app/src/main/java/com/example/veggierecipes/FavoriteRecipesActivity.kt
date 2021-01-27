package com.example.veggierecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_favorite_recipes.*

class FavoriteRecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_recipes)

        supportActionBar?.title = "Favorites"


        getRecipes()

    }

    private fun getRecipes() {
        val ref = FirebaseDatabase.getInstance().getReference("/recipes")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            val adapter = GroupAdapter<ViewHolder>()
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    Log.d("asd", it.toString())
                    val recipe = it.getValue(Recipe::class.java)
                    if (recipe != null) {
                        adapter.add(RecipeItem(recipe))
                    }
                    adapter.setOnItemClickListener { item, view ->
                        val intent = Intent(view.context,DetailedRecipeActivity::class.java)
                        startActivity(intent)
                    }
                }
                recyclerview_favoriterecipes.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}