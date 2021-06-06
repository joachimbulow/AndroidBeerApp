package com.beehive.beerrate.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.Beer
import com.bumptech.glide.Glide

class CardStackAdapter(private var beers: List<Beer> = emptyList()) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var image: ImageView = view.findViewById(R.id.item_image)
        private val beerName: TextView = view.findViewById(R.id.beer_name)
        private val location: TextView = view.findViewById(R.id.beer_location)

        fun bind(beer: Beer) {
            Glide.with(image).load(beer.imageUrl).into(image)
            beerName.text = beer.beerName
            location.text = "${beer.city}, ${beer.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.beer_cardview, parent, false))
    }

    override fun getItemCount(): Int = beers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(beers[position])

    fun getItem(position: Int): Beer = beers[position]
}