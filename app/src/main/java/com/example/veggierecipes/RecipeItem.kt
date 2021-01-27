package com.example.veggierecipes

import com.bumptech.glide.Glide
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.recipe.view.*

class RecipeItem(private val recipe:Recipe):Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.recipe_name.text = recipe.name
        Glide.with(viewHolder.itemView.recipe_background).load(recipe.imageUrl)
            .into(viewHolder.itemView.recipe_background)

    }

    override fun getLayout(): Int {
        return R.layout.recipe
    }
}