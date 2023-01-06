package com.chachaup.irecipe.vm

import androidx.lifecycle.ViewModel
import com.chachaup.irecipe.model.MealsRepo

class CookdVM(private val repo: MealsRepo): ViewModel() {
    lateinit var meal:String
    lateinit var instructions: String
    lateinit var ingredient1: String
    lateinit var ingredient2: String
    lateinit var ingredient3: String
    lateinit var ingredient4: String
    lateinit var ingredient5: String
    lateinit var ingredient6: String
    lateinit var ingredient7: String
    lateinit var ingredient8: String
    lateinit var measure1: String
    lateinit var measure2: String
    lateinit var measure3: String
    lateinit var measure4: String
    lateinit var measure5: String
    lateinit var measure6: String
    lateinit var measure7: String
    lateinit var measure8: String
}