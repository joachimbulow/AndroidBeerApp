package com.beehive.beerrate.ui.search

import android.os.AsyncTask
import androidx.lifecycle.*
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val exploreRepository: ExploreRepository) : ViewModel() {

    //Search logic

    val mutableSearchString: MutableLiveData<String> = MutableLiveData()

    fun searchForBeer(query: String) {
        mutableSearchString.value = query;
    }

    val beerObservable: LiveData<List<Beer>> = Transformations.switchMap(mutableSearchString) { query ->
        exploreRepository.searchBeer(query).asLiveData()
    }

    fun updateBeer(beer: Beer){
        UpdateBeerAsyncTask(exploreRepository, beer).execute()
    }

    // // // //

    companion object {
        private class UpdateBeerAsyncTask(private val exploreRepository: ExploreRepository, private val beer: Beer): AsyncTask<Beer, Void, Void>(){
            override fun doInBackground(vararg p0: Beer?): Void? {
                exploreRepository.updateBeer(beer)
                return null
            }

        }
    }


    private val _text = MutableLiveData<String>().apply {
        value = "Search for beers"
    }
    val text: LiveData<String> = _text
}