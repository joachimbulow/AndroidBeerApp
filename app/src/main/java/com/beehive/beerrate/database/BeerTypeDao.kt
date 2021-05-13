package com.beehive.beerrate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.beehive.beerrate.model.BeerType
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerTypeDao {

    @Query("SELECT * FROM beertypes WHERE beertype_id = :id")
    fun load(id: Int): Flow<BeerType>

    @Query("SELECT * FROM beertypes")
    fun getAll(): Flow<List<BeerType>>

    // Get all preferred types of beer
    @Query("SELECT * FROM beertypes WHERE preferred = 1")
    fun getAllPreferred(): Flow<List<BeerType>>

    @Query("UPDATE beertypes SET preferred = :preferred WHERE beertype_id = :id")
    fun updatePreferred(id: Int, preferred: Boolean)

    @Insert(onConflict = REPLACE)
    fun save(beerType: BeerType)

    @Insert
    fun saveAll(beerTypes: List<BeerType>)

    @Query("SELECT COUNT(*) FROM beertypes")
    fun getCount(): Int
}