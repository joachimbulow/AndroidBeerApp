package com.beehive.beerrate.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.model.BeerType
import com.beehive.beerrate.ui.preference.BeerTypeAdapter

class BeerAdapter(var beers: List<Beer>) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    inner class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerViewText: TextView = itemView.findViewById(R.id.beerText)

        fun bind(beer: Beer) {
            beerViewText.text = beer.beerName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.beerview, parent, false)
        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        println("onBind")
        return holder.bind(beers[position])
    }

    override fun getItemCount(): Int {
        return beers.size
    }
}