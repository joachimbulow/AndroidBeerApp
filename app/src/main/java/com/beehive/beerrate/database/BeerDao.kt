package com.beehive.beerrate.database

import androidx.room.Dao
import androidx.room.Query
import com.beehive.beerrate.model.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Query("SELECT * FROM beers WHERE preferred = 0")
    fun getAllNonPreferredBeers(): Flow<List<Beer>>

}