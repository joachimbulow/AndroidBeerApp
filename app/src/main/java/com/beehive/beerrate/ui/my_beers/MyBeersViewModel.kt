package com.beehive.beerrate.ui.my_beers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.beehive.beerrate.repository.MyBeersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyBeersViewModel @Inject constructor(myBeersRepository: MyBeersRepository) : ViewModel() {

    val prefBeers = myBeersRepository.getPreferredBeers().asLiveData()
}