package com.beehive.beerrate.repository

import com.beehive.beerrate.database.BeerDao
import com.beehive.beerrate.model.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExploreRepository @Inject constructor(
    private val beerDao: BeerDao
) {

    fun getAllNonPreferredBeers(): Flow<List<Beer>> {
        return beerDao.getAllNonPreferredBeers()
    }

    fun getAllNonPreferredBeersRandomOrder(): Flow<List<Beer>> {
        return beerDao.getAllNonPreferredBeersRandomOrder()
    }

    fun updateBeer(beer: Beer) {
        return beerDao.updateBeer(beer)
    }

    fun searchBeer(searchString: String): Flow<List<Beer>> {
        return beerDao.searchBeer(searchString)
    }
}