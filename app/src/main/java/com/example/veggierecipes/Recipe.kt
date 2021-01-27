package com.example.veggierecipes

class Recipe(val id:String, val credit:String, val imageUrl:String, val ingredients:MutableList<String>?, val instructions:String, val name:String) {
constructor() : this("","","", mutableListOf<String>(""),"","")
}