package com.beehive.beerrate.ui.search

import androidx.lifecycle.*
import com.beehive.beerrate.async.UpdateBeerAsyncTask
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val exploreRepository: ExploreRepository) : ViewModel() {

    //Search logic
    private val mutableSearchString: MutableLiveData<String> = MutableLiveData()

    fun searchForBeer(query: String) {
        mutableSearchString.value = query
    }

    val beerObservable: LiveData<List<Beer>> = Transformations.switchMap(mutableSearchString) { query ->
        exploreRepository.searchBeer(query).asLiveData()
    }

    fun updateBeer(beer: Beer) {
        UpdateBeerAsyncTask(exploreRepository).execute(beer)
    }
}