package com.example.veggierecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
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
        refreshApp()
        getRecipes()

    }
    private fun refreshApp(){
        findViewById<androidx.swiperefreshlayout.widget.SwipeRefreshLayout>(R.id.swipetorefresh).setOnRefreshListener{
            finish()
            startActivity(intent)
            swipetorefresh.isRefreshing = false
        }
    }
    private fun getRecipes() {
        val uid =FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid/favorites")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            val adapter = GroupAdapter<ViewHolder>()
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    Log.d("asd", it.toString())
                    val recipe = it.getValue(Recipe::class.java)
                    if (recipe != null) {
                        adapter.add(RecipeItem(recipe,this@FavoriteRecipesActivity))
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