package com.chachaup.irecipe.vm

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.data.User
import com.chachaup.irecipe.model.MealsRepo
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class CookdVM @ViewModelScoped constructor(private val repo: MealsRepo) : ViewModel() {

    @Inject lateinit var firebaseAuth: FirebaseAuth
    lateinit var mealObject: Meal

    val bottomNavigationVisibility: MutableLiveData<Int> by lazy { MutableLiveData<Int>()}
    /** This is not a good practice.
    variables should not be modified outside the view-model.
    Instead, state flows should be used.

    Although this is not as effective as state flows, mutable live data may also be used */

    //    Sample implementation of mutable live data
    private val _mealObject: MutableLiveData<Meal> by lazy { MutableLiveData<Meal>() }
    val mealObject1: LiveData<Meal> get() = _mealObject

    fun updateBottomNavVisibility(isLoggedIn: Boolean){
        bottomNavigationVisibility.value = if (isLoggedIn){
            View.VISIBLE
        } else{
            View.GONE
        }
    }

     fun createUser(user: User): Task<AuthResult> {
         return firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

}