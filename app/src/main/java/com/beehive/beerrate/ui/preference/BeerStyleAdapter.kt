package com.beehive.beerrate.ui.preference

import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.BeerStyle

class BeerStyleAdapter(var beerStyles: List<BeerStyle>) :
    RecyclerView.Adapter<BeerStyleAdapter.BeerStyleViewHolder>() {

    inner class BeerStyleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerStyleTextView: TextView = itemView.findViewById(R.id.beer_style_textView)
        private val beerStyleDescriptionTextView: TextView =
            itemView.findViewById(R.id.beer_style_description)


        fun bind(beerStyle: BeerStyle) {
            beerStyleTextView.text = beerStyle.name
            beerStyleDescriptionTextView.text = beerStyle.description
            var expanded = false
            beerStyleDescriptionTextView.setOnClickListener {
                if(expanded) {
                    beerStyleDescriptionTextView.maxLines = 3
                    expanded = false
                } else {
                    beerStyleDescriptionTextView.maxLines = Integer.MAX_VALUE
                    expanded = true
                }
            }
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