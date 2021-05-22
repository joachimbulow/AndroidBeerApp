package com.beehive.beerrate.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.beehive.beerrate.repository.BeerPreferenceRepository
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val exploreRepository: ExploreRepository) : ViewModel() {

    val searchResults = exploreRepository.searchBeer().asLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "Search for beers"
    }
    val text: LiveData<String> = _text
}