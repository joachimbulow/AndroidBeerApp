package com.beehive.beerrate.ui.explore


import android.os.AsyncTask
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import com.beehive.beerrate.helper.getDistinct
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val exploreRepository: ExploreRepository) :
    ViewModel() {

    var allNonPreferredBeers = exploreRepository.getAllNonPreferredBeersRandomOrder().asLiveData()

    fun updateBeer(beers: List<Beer>) {
        UpdateBeerAsyncTask(exploreRepository).execute(*beers.toTypedArray())
    }


    companion object {
        private class UpdateBeerAsyncTask(
            private val exploreRepository: ExploreRepository
        ) : AsyncTask<Beer, Void, Void>() {
            override fun doInBackground(vararg params: Beer?): Void? {
                for (beer in params) {
                    exploreRepository.updateBeer(beer!!)
                }
                return null
            }
        }
    }
}