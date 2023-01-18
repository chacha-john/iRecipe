package com.chachaup.irecipe

import android.app.Application
import com.chachaup.irecipe.model.MealsDatabase
import com.chachaup.irecipe.model.MealsRepo

class IRecipeApplication: Application() {
    private val database by lazy { MealsDatabase.getDatabase(applicationContext) }
    val repo by lazy { MealsRepo(database.mealsDao()) }
}