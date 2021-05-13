package com.beehive.beerrate.di

import android.content.Context
import androidx.room.Room
import com.beehive.beerrate.database.BeerPreferenceDatabase
import com.beehive.beerrate.database.BeerStyleDao
import com.beehive.beerrate.database.BeerTypeDao
import com.beehive.beerrate.service.BeerService
import com.beehive.beerrate.service.BeerServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBeerPreferenceDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, BeerPreferenceDatabase::class.java, "beerdb")
            .createFromAsset("database/beerdb.db")
            .build()

    @Singleton
    @Provides
    fun provideBeerTypeDao(db: BeerPreferenceDatabase): BeerTypeDao {
        return db.beerTypeDao()
    }

    @Singleton
    @Provides
    fun provideBeerStyleDao(db: BeerPreferenceDatabase): BeerStyleDao {
        return db.beerStyleDao()
    }

    @Singleton
    @Provides
    fun provideBeerService(): BeerService {
        return BeerServiceGenerator.createService(BeerService::class.java)
    }

}
