package com.example.veggierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailedRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_recipe)

        supportActionBar?.title = "recipe name"
    }
}