package com.beehive.beerrate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.beehive.beerrate.model.BeerType
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerTypeDao {

    @Query("SELECT * FROM beertypes WHERE beertype_id = :beerTypeUid")
    fun load(beerTypeUid: Int): Flow<BeerType>

    @Query("SELECT * FROM beertypes")
    fun getAll(): Flow<List<BeerType>>

    @Insert(onConflict = REPLACE)
    fun save(beerType: BeerType)

    @Insert
    fun saveAll(beerTypes: List<BeerType>)

    @Query("SELECT COUNT(*) FROM beertypes")
    fun getCount(): Int
}