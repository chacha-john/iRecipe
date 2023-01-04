package com.chachaup.irecipe.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_meals")
data class MealItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val pictureUrl: String
)