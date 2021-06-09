package com.beehive.beerrate.repository

import com.beehive.beerrate.database.BeerStyleDao
import com.beehive.beerrate.database.BeerTypeDao
import com.beehive.beerrate.model.BeerStyle
import com.beehive.beerrate.model.BeerType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerPreferenceRepository @Inject constructor(private val beerTypeDao: BeerTypeDao, private val beerStyleDao: BeerStyleDao) {

    fun getBeerTypes(): Flow<List<BeerType>> {
        return beerTypeDao.getAll()
    }

    fun getBeerTypePreferences(): Flow<List<BeerType>> {
        return beerTypeDao.getAllPreferred()
    }

    fun updatePreferredBeerType(beerType: BeerType) {
        beerTypeDao.updatePreferred(beerType.uid, beerType.preferred)
    }

    fun getBeerStyles(): Flow<List<BeerStyle>> {
        return beerStyleDao.getAll()
    }

    fun getAllBasedOnPreferredBeerTypes(): Flow<List<BeerStyle>> {
        return beerStyleDao.getAllBasedOnPreferredBeerTypes()
    }

    fun getBeerStylePreferences(): Flow<List<BeerStyle>> {
        return beerStyleDao.getAllPreferred()
    }

    fun updatePreferredBeerStyle(beerStyle: BeerStyle) {
        beerStyleDao.updatePreferred(beerStyle.uid, beerStyle.preferred)
    }

}