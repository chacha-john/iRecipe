package com.chachaup.irecipe.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.data.MealItem

@Dao
interface MealsDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertMeal(mealItem: MealItem)

    @Query("SELECT * FROM tbl_meals")
    suspend fun getAllMeals(): List<MealItem>
}