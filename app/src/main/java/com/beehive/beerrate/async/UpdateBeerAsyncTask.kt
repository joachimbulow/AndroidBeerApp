package com.beehive.beerrate.async

import android.os.AsyncTask
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.repository.ExploreRepository

class UpdateBeerAsyncTask(private val exploreRepository: ExploreRepository) : AsyncTask<Beer, Void, Void>() {
    override fun doInBackground(vararg beers: Beer?): Void? {
        beers.forEach { it?.let(exploreRepository::updateBeer) }
        return null
    }
}