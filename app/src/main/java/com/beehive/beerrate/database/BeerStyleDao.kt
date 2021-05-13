package com.beehive.beerrate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beehive.beerrate.model.BeerStyle
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerStyleDao {

    @Query("SELECT * FROM beerstyles WHERE beerstyle_id = :beerStyleUid")
    fun load(beerStyleUid: Int): Flow<BeerStyle>

    @Query("SELECT * FROM beerstyles")
    fun getAll(): Flow<List<BeerStyle>>

    @Query("SELECT * FROM beerstyles WHERE preferred = 1")
    fun getAllPreferred(): Flow<List<BeerStyle>>

    @Query("UPDATE beerstyles SET preferred = :preferred WHERE beerstyle_id = :id")
    fun updatePreferred(id: Int, preferred: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(beerStyle: BeerStyle)

    @Insert
    suspend fun saveAll(beerStyles: List<BeerStyle>)

    @Query("SELECT COUNT(*) FROM beerstyles")
    fun getCount(): Int
}