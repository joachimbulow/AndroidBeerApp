package com.beehive.beerrate.repository

import com.beehive.beerrate.database.BeerDao
import com.beehive.beerrate.model.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyBeersRepository @Inject constructor(private val beerDao: BeerDao) {

    fun getPreferredBeers(): Flow<List<Beer>> {
        return beerDao.getPreferredBeers()
    }
}