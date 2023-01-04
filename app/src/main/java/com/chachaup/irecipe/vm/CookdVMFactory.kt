package com.chachaup.irecipe.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chachaup.irecipe.model.MealsRepo

class CookdVMFactory(private val repo: MealsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CookdVM::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CookdVM(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}