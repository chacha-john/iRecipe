package com.chachaup.irecipe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chachaup.irecipe.data.MealItem
import com.chachaup.irecipe.model.MealsDao

@Database(entities = [MealItem::class], version = 1, exportSchema = false)
abstract class CookdDB: RoomDatabase() {
    abstract fun mealsDao(): MealsDao

    companion object{
        private var INSTANCE: CookdDB? = null

        fun getInstance(context: Context): CookdDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    CookdDB::class.java,
                    "cookd_db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}