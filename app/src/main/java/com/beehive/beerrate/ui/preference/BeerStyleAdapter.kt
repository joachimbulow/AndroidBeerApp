package com.beehive.beerrate.ui.preference

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.BeerStyle

class BeerStyleAdapter(var beerStyles: List<BeerStyle>) :
    RecyclerView.Adapter<BeerStyleAdapter.BeerStyleViewHolder>() {

    inner class BeerStyleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerStyleTextView: TextView = itemView.findViewById(R.id.beer_style_textView)

        fun bind(beerStyle: BeerStyle) {
            beerStyleTextView.text = beerStyle.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerStyleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.beer_style_textview, parent, false)
        return BeerStyleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return beerStyles.size
    }

    override fun onBindViewHolder(holder: BeerStyleViewHolder, position: Int) {
        return holder.bind(beerStyles[position])
    }
}