package com.beehive.beerrate.ui.preference

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.BeerStyle

class BeerStyleEditAdapter(var allBeerStyles: List<BeerStyle>) :
    RecyclerView.Adapter<BeerStyleEditAdapter.EditBeerStyleViewHolder>() {
    inner class EditBeerStyleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerStyleEditTextView: TextView = itemView.findViewById(R.id.beer_style_edit_textview)

        fun bind(beerStyle: BeerStyle) {
            beerStyleEditTextView.text = beerStyle.name
            beerStyleEditTextView.setBackgroundColor(if (beerStyle.preferred) Color.GREEN else Color.WHITE)
            beerStyleEditTextView.setOnClickListener {
                beerStyle.preferred = !beerStyle.preferred
                beerStyleEditTextView.setBackgroundColor(if (beerStyle.preferred) Color.GREEN else Color.WHITE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditBeerStyleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.beer_style_edit_textview, parent, false)
        return EditBeerStyleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allBeerStyles.size
    }

    override fun onBindViewHolder(holder: EditBeerStyleViewHolder, position: Int) {
        return holder.bind(allBeerStyles[position])
    }
}