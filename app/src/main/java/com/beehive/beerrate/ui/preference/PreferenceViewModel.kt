package com.beehive.beerrate.ui.preference

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.*
import com.beehive.beerrate.model.BeerStyle
import com.beehive.beerrate.model.BeerType
import com.beehive.beerrate.repository.BeerPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreferenceViewModel @Inject constructor(private val beerPreferenceRepository: BeerPreferenceRepository) :
    ViewModel() {

    val allBeerTypes = beerPreferenceRepository.getBeerTypes().asLiveData()
    val preferredBeerTypes = beerPreferenceRepository.getBeerTypePreferences().asLiveData()
    val allBeerStyles = beerPreferenceRepository.getBeerStyles().asLiveData()
    val preferredBeerStyles = beerPreferenceRepository.getBeerStylePreferences().asLiveData()

    fun updateBeerTypePreferences() {
        val diff =
            allBeerTypes.value!!.minus(preferredBeerTypes.value!!.toHashSet())
        val preferredStyles = allBeerStyles.value!!
        UpdateBeerTypesAsyncTask(
            beerPreferenceRepository,
            preferredStyles
        ).execute(*diff.toTypedArray())
    }

    fun updateBeerStylePreferences() {
        val diff = allBeerStyles.value!!.minus(preferredBeerStyles.value!!.toHashSet())
        UpdateBeerStylesAsyncTask(beerPreferenceRepository)
            .execute(*diff.toTypedArray())
    }

    companion object {

        private class UpdateBeerTypesAsyncTask(
            private val beerPreferenceRepository: BeerPreferenceRepository,
            private val allBeerStyles: List<BeerStyle>
        ) :
            AsyncTask<BeerType, Void, Void>() {
            override fun doInBackground(vararg params: BeerType?): Void? {
                for (beerType in params) {
                    beerPreferenceRepository.updatePreferredBeerType(beerType!!)
                    for (beerStyle in allBeerStyles) {
                        if (!beerType.preferred && beerStyle.beerTypeId == beerType.uid) {
                            beerStyle.preferred = false
                            beerPreferenceRepository.updatePreferredBeerStyle(beerStyle)
                        } else if (beerStyle.beerTypeId == beerType.uid) {
                            beerStyle.preferred = true
                            beerPreferenceRepository.updatePreferredBeerStyle(beerStyle)
                        }
                    }
                }
                return null
            }
        }

        private class UpdateBeerStylesAsyncTask(
            private val beerPreferenceRepository: BeerPreferenceRepository
        ) : AsyncTask<BeerStyle, Void, Void>() {
            override fun doInBackground(vararg params: BeerStyle?): Void? {
                for (beerStyle in params) {
                    beerStyle!!.preferred = false
                    beerPreferenceRepository.updatePreferredBeerStyle(beerStyle)
                }
                return null
            }
        }
    }

}