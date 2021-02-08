package com.ayeshaazeema.recipeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun getLaunchService(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    private val list = ArrayList<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        rv_main.setHasFixedSize(true)
        list.addAll(getRecipe())
        showRecycler()
    }

    private fun showRecycler() {
        rv_main.layoutManager = LinearLayoutManager(this)
        val listRecipe = ItemAdapter(list)
        rv_main.adapter = listRecipe
    }

    private fun getRecipe(): ArrayList<Recipe> {
        val dataName = resources.getStringArray(R.array.name_food)
        val dataDesc = resources.getStringArray(R.array.desc_food)
        val dataIngredients = resources.getStringArray(R.array.ingredients)
        val dataSteps = resources.getStringArray(R.array.steps)
        val dataRating = resources.getIntArray(R.array.rating)
        val dataPhoto = resources.getStringArray(R.array.photo_food)
        val listRecipes = ArrayList<Recipe>()
        for (position in dataName.indices) {
            val recipes = Recipe(
                dataName[position],
                dataDesc[position],
                dataIngredients[position],
                dataSteps[position],
                dataRating[position],
                dataPhoto[position]
            )
            listRecipes.add(recipes)
        }
        return listRecipes

    }

}