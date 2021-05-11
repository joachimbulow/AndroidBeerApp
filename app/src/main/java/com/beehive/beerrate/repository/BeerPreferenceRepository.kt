package com.beehive.beerrate.repository

import android.util.Log
import com.beehive.beerrate.database.BeerStyleDao
import com.beehive.beerrate.database.BeerTypeDao
import com.beehive.beerrate.model.BeerStyle
import com.beehive.beerrate.model.BeerType
import com.beehive.beerrate.service.BeerService
import com.beehive.beerrate.service.BeerServiceGenerator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerPreferenceRepository @Inject constructor(
     val beerTypeDao: BeerTypeDao,
     val beerStyleDao: BeerStyleDao
) {
    fun getBeerTypePreferences(): Flow<List<BeerType>> {
        return beerTypeDao.getAll()
    }

    fun getBeerStylePreferences(): Flow<List<BeerStyle>> {
        return  beerStyleDao.getAll()
    }

}