package com.chachaup.irecipe.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepo @Inject constructor(private val dao: MealsDao) {
}