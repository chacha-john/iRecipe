package com.chachaup.irecipe

import android.app.Application
import com.chachaup.irecipe.model.MealsDatabase
import com.chachaup.irecipe.model.MealsRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class IRecipeApplication: Application() {
    private val database by lazy { MealsDatabase.getDatabase(applicationContext) }
    val repo by lazy { MealsRepo(database.mealsDao()) }

    @Inject lateinit var firebaseAuth: FirebaseAuth
}