package com.beehive.beerrate.ui.preference

import androidx.lifecycle.*
import com.beehive.beerrate.repository.BeerPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PreferenceViewModel @Inject constructor(public val beerPreferenceRepository: BeerPreferenceRepository) :
    ViewModel() {

    val beerTypes = beerPreferenceRepository.getBeerTypePreferences().asLiveData()
    val beerStyles = beerPreferenceRepository .getBeerStylePreferences().asLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is preference Fragment"
    }

    val text: LiveData<String> = _text
}