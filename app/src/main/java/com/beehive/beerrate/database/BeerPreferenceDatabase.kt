package com.beehive.beerrate.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.model.BeerStyle
import com.beehive.beerrate.model.BeerType

@Database(entities = [BeerType::class, BeerStyle::class, Beer::class], version = 1)
abstract class BeerPreferenceDatabase : RoomDatabase() {
    abstract fun beerTypeDao(): BeerTypeDao
    abstract fun beerStyleDao(): BeerStyleDao
    abstract fun beerDao(): BeerDao
}