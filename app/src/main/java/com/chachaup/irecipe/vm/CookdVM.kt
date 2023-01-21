package com.chachaup.irecipe.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.model.MealsRepo

class CookdVM(private val repo: MealsRepo) : ViewModel() {

    lateinit var mealObject: Meal
    /** This is not a good practice.
    variables should not be modified outside the view-model.
    Instead, state flows should be used.

    Although this is not as effective as state flows, mutable live data may also be used */

    //    Sample implementation of mutable live data
    private val _mealObject: MutableLiveData<Meal> by lazy { MutableLiveData<Meal>() }
    val mealObject1: LiveData<Meal> get() = _mealObject

}