package com.beehive.beerrate.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.ui.beerbottomsheet.BeerBottomSheetFragment

class BeerAdapter(var beers: List<Beer>, var viewmodel: SearchViewModel, val fragmentManager: FragmentManager) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    inner class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerViewText: TextView = itemView.findViewById(R.id.beerText)
        private val brewerNameText: TextView = itemView.findViewById(R.id.brewerNameText)
        private val countryCodeText: TextView = itemView.findViewById(R.id.countryCodeText)
        val preferBtn: Button = itemView.findViewById(R.id.preferBtn)

        fun bind(beer: Beer) {
            beerViewText.text = beer.beerName
            brewerNameText.text = beer.brewerName
            countryCodeText.text = beer.code
            preferBtn.text = if (beer.preferred) "Non-prefer" else "Prefer"
            itemView.setOnClickListener { BeerBottomSheetFragment(beer).show(fragmentManager, "MyBeerBottomSheet") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.beerview, parent, false))
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        //On click for toggling preference
        holder.preferBtn.setOnClickListener {
            //For UI
            val mutableBeers = beers.toMutableList()
            toggleBeerPreference(mutableBeers[position])
            beers = mutableBeers.toList()

            //Actually preferring a beer
            viewmodel.updateBeer(beers[position])
        }
        //Handle binding
        return holder.bind(beers[position])
    }

    override fun getItemCount(): Int = beers.size

    private fun toggleBeerPreference(beer: Beer) {
        beer.preferred = !beer.preferred
    }
}