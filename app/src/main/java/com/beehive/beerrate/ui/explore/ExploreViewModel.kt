package com.beehive.beerrate.ui.explore


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(exploreRepository: ExploreRepository) :
    ViewModel() {

    val allNonPreferredBeers = exploreRepository.getAllNonPreferredBeers().asLiveData()
}