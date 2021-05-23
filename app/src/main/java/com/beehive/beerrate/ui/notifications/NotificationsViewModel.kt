package com.beehive.beerrate.ui.notifications

import androidx.lifecycle.*
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.repository.BeerPreferenceRepository
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(private val exploreRepository: ExploreRepository) : ViewModel() {

    //Search logic

    val mutableSearchString: MutableLiveData<String> = MutableLiveData()

    fun searchForBeer(query: String) {
        mutableSearchString.value = query;
    }

    val beerObservable: LiveData<List<Beer>> = Transformations.switchMap(mutableSearchString) { query ->
        exploreRepository.searchBeer(query).asLiveData()
    }

    // // // //


    private val _text = MutableLiveData<String>().apply {
        value = "Search for beers"
    }
    val text: LiveData<String> = _text
}