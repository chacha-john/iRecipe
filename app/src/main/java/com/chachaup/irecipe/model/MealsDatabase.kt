package com.chachaup.irecipe.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chachaup.irecipe.data.MealItem

@Database(entities = [MealItem::class], version = 1, exportSchema = false)
abstract class MealsDatabase: RoomDatabase() {
    abstract fun mealsDao(): MealsDao

    companion object{
        @Volatile
        private var INSTANCE: MealsDatabase? = null

        fun getDatabase(context: Context): MealsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDatabase::class.java,
                    "meals_database"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}