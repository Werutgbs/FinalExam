package com.example.veggierecipes

import java.io.Serializable

class Recipe(val id:String, val credit:String, val imageUrl:String, val ingredients:MutableList<String>?, val instructions:String, val name:String) : Serializable {
constructor() : this("","","", mutableListOf<String>(""),"","")
}