package com.beehive.beerrate.ui.preference

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.BeerType

class BeerTypeAdapter(var beerTypes: List<BeerType>) :
    RecyclerView.Adapter<BeerTypeAdapter.BeerTypeViewHolder>() {

    inner class BeerTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerTypeTextView: TextView = itemView.findViewById(R.id.beer_type_textView)

        fun bind(beerType: BeerType) {
            beerTypeTextView.text = beerType.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerTypeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.beer_type_textview, parent, false)
        return BeerTypeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return beerTypes.size
    }

    override fun onBindViewHolder(holder: BeerTypeViewHolder, position: Int) {
        return holder.bind(beerTypes[position])
    }
}