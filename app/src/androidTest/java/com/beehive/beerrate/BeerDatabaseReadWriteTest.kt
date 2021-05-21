package com.beehive.beerrate

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beehive.beerrate.database.BeerPreferenceDatabase
import com.beehive.beerrate.model.BeerType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeerDatabaseReadWriteTest {

    private lateinit var db: BeerPreferenceDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BeerPreferenceDatabase::class.java)
            .createFromAsset("database/beerdb.db").build()
    }


    @Test
    fun readBeerTypes() {
        val beerTypes: Flow<List<BeerType>> = db.beerTypeDao().getAll()
        runBlocking {
            val toList = beerTypes.toList()
            Log.d("tag", toList.toString())
        }
    }
}