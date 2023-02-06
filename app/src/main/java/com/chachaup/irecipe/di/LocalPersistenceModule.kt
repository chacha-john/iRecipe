package com.chachaup.irecipe.di

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
        fun provideMealsRepo(dao: MealsDao): MealsRepo{
            return MealsRepo(dao)
        }
}