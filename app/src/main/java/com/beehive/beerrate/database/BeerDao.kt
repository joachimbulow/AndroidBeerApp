package com.beehive.beerrate.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.beehive.beerrate.model.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Query("SELECT * FROM beers WHERE preferred = 0")
    fun getAllNonPreferredBeers(): Flow<List<Beer>> // Flow - whenever a beer in the database is updated, then the entire list of beers is emitted again. Keeps UI up to date. (Entire query needs to be re-executed as SQLite only supports table level notification)

    @Query("SELECT * FROM beers WHERE beerstyle_id IN (SELECT beerstyle_id FROM beerstyles WHERE preferred = 1) AND preferred = 0 ORDER BY RANDOM() LIMIT 100")
    fun getAllNonPreferredBeersRandomOrder(): Flow<List<Beer>>

    @Query("SELECT * FROM beers WHERE preferred = 1")
    fun getPreferredBeers(): Flow<List<Beer>>

    @Update
    fun updateBeer(beer: Beer)

    @Query("SELECT * FROM beers WHERE beer_name LIKE '%' || :searchString || '%' OR brewer_name LIKE '%' || :searchString || '%' OR city LIKE '%' || :searchString || '%'")
    fun searchBeer(searchString: String): Flow<List<Beer>>


}