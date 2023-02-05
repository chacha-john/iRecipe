package com.chachaup.irecipe.network

import com.chachaup.irecipe.BuildConfig
import com.chachaup.irecipe.data.Meal
import com.chachaup.irecipe.data.MealResponseItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealInterface {
    @GET("search.php")
    suspend fun getMeals(
        @Query("s") meal: String
    ): MealResponseItem

    companion object{
        operator fun invoke(): MealInterface{
            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient
                .Builder()
                .apply { this.addInterceptor(interceptor) }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.MDB_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(MealInterface::class.java)
        }
    }
}