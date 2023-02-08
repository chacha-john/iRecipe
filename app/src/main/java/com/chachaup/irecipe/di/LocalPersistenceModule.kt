package com.chachaup.irecipe.di

import android.app.Application
import com.chachaup.irecipe.db.CookdDB
import com.chachaup.irecipe.model.MealsDao
import com.chachaup.irecipe.model.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalPersistenceModule {

    @Singleton
    @Provides
    fun provideMealsRepo(dao: MealsDao): MealsRepo {
        return MealsRepo(dao)
    }

    @Provides
    @Singleton
    fun provideMealsDao(db: CookdDB): MealsDao {
        return db.mealsDao()
    }

    @Provides
    @Singleton
    fun provideCookdDB(application: Application): CookdDB {
        return CookdDB.getInstance(application)
    }
}