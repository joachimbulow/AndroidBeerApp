package com.beehive.beerrate.ui.explore

import androidx.lifecycle.*
import com.beehive.beerrate.async.UpdateBeerAsyncTask
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val exploreRepository: ExploreRepository) : ViewModel() {

    val allNonPreferredBeers = exploreRepository.getAllNonPreferredBeersRandomOrder().asLiveData()

    fun updateBeer(beer: Beer) {
        UpdateBeerAsyncTask(exploreRepository).execute(beer)
    }
}