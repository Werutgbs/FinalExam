package com.example.veggierecipes

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.recipe.view.*

class RecipeItem(private val recipe: Recipe,private val context: Context) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.recipe_name.text = recipe.name
        Glide.with(viewHolder.itemView.recipe_background).load(recipe.imageUrl)
            .into(viewHolder.itemView.recipe_background)

        isFav(recipe,viewHolder.itemView)
        viewHolder.itemView.favorite_recipe_button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {


                viewHolder.itemView.favorite_recipe_button.setBackgroundResource(R.drawable.ic_favorite_filled_24)
                makeFavorite(recipe)

            } else {


                viewHolder.itemView.favorite_recipe_button.setBackgroundResource(R.drawable.ic_favorite_shadow_24)
                removeFavorite(recipe)

            }
        }

        viewHolder.itemView.setOnClickListener {

            val intent = Intent(context,DetailedRecipeActivity::class.java)
            intent.putExtra("recipe",recipe)
            context.startActivity(intent)

        }

    }
//TODO add credits
    private fun removeFavorite(recipe: Recipe) {

        val uid = FirebaseAuth.getInstance().uid
        val ref = Firebase.database.getReference("users/$uid/favorites/${recipe.id}")
        ref.removeValue()
        Toast.makeText(context,"Removed from favorites",Toast.LENGTH_SHORT).show()

    }

    private fun makeFavorite(recipe: Recipe) {

        val uid = FirebaseAuth.getInstance().uid
        val ref = Firebase.database.getReference("users/$uid/favorites/${recipe.id}")
        ref.setValue(recipe)
        Toast.makeText(context,"Added to favorites",Toast.LENGTH_SHORT).show()

    }

    private fun isFav(recipe: Recipe,view : View)  {

        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid/favorites/")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                view.favorite_recipe_button.isChecked = snapshot.hasChild(recipe.id)
            }

            override fun onCancelled(error: DatabaseError) {
//TODO error msg           ^^^^^^
            }

        })


    }

    override fun getLayout(): Int {
        return R.layout.recipe
    }
}