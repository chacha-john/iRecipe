package com.chachaup.irecipe.vm

import androidx.lifecycle.ViewModel
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.model.MealsRepo

class CookdVM(private val repo: MealsRepo): ViewModel() {

    lateinit var mealObject: Meal
}