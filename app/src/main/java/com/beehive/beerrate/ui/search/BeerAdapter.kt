package com.beehive.beerrate.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.Beer

class BeerAdapter(var beers: List<Beer>) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    inner class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerViewText: TextView = itemView.findViewById(R.id.beerText)
        private val brewerNameText: TextView = itemView.findViewById(R.id.brewerNameText)
        private val countryCodeText: TextView = itemView.findViewById(R.id.countryCodeText)

        fun bind(beer: Beer) {
            beerViewText.text = beer.beerName
            brewerNameText.text = beer.brewerName
            countryCodeText.text = beer.code
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.beerview, parent, false)
        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        return holder.bind(beers[position])
    }

    override fun getItemCount(): Int {
        return beers.size
    }
}