package com.beehive.beerrate.ui.explore


import android.os.AsyncTask
import androidx.lifecycle.*
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val exploreRepository: ExploreRepository) : ViewModel() {

    val allNonPreferredBeers = exploreRepository.getAllNonPreferredBeersRandomOrder().asLiveData()

    fun updateBeer(beers: List<Beer>) {
        UpdateBeerAsyncTask(exploreRepository).execute(*beers.toTypedArray())
    }

    companion object {
        private class UpdateBeerAsyncTask(private val exploreRepository: ExploreRepository) : AsyncTask<Beer, Void, Void>() {
            override fun doInBackground(vararg beers: Beer?): Void? {
                beers.forEach { it?.let(exploreRepository::updateBeer) }
                return null
            }
        }
    }
}