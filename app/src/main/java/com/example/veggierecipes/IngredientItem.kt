package com.example.veggierecipes

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.ingredients.view.*

class IngredientItem(private val ingredient: String) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.ingredient.text = ingredient


    }


    override fun getLayout(): Int {
        return R.layout.ingredients
    }
}