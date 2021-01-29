package com.example.veggierecipes

class User(val uid:String,val username:String,val profileImageUrl:String?,val favoriteRecipes:MutableList<Recipe>?=null){
}
