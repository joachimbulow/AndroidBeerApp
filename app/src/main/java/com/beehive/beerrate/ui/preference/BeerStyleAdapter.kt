package com.beehive.beerrate.ui.preference

import android.graphics.Color
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.BeerStyle

class BeerStyleAdapter(private var beerStyles: List<BeerStyle>) : RecyclerView.Adapter<BeerStyleAdapter.BeerStyleViewHolder>() {

    var editMode = false

    inner class BeerStyleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerStyleTextView: TextView = itemView.findViewById(R.id.beer_style_textView)
        private val beerStyleDescriptionTextView: TextView = itemView.findViewById(R.id.beer_style_description)

        fun bind(beerStyle: BeerStyle) {
            beerStyleTextView.text = beerStyle.name
            if (!editMode) {
                beerStyleTextView.setTextColor(-1979711488)
                beerStyleDescriptionTextView.visibility = View.VISIBLE
                beerStyleDescriptionTextView.text = beerStyle.description
                var expanded = false
                beerStyleDescriptionTextView.setOnClickListener {
                    if (expanded) {
                        beerStyleDescriptionTextView.maxLines = 3
                    } else beerStyleDescriptionTextView.maxLines = Integer.MAX_VALUE
                    expanded = !expanded
                }
            } else {
                setEntryStyle(beerStyle)
                beerStyleDescriptionTextView.visibility = View.GONE
                beerStyleTextView.setOnClickListener {
                    beerStyle.preferred = !beerStyle.preferred
                    setEntryStyle(beerStyle)
                }
            }
        }

        private fun setEntryStyle(beerStyle: BeerStyle) {
            beerStyleTextView.setTextColor(if (beerStyle.preferred) Color.parseColor("#2e7d32") else Color.RED)
            beerStyleTextView.text = itemView.resources.getString(R.string.beer_style, if (beerStyle.preferred) "✔   " else "✖   ", beerStyle.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerStyleViewHolder {
        return BeerStyleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.beer_style_textview, parent, false))
    }

    override fun getItemCount(): Int = beerStyles.size

    override fun onBindViewHolder(holder: BeerStyleViewHolder, position: Int) = holder.bind(beerStyles[position])

    fun setBeerStyles(beerStyles: List<BeerStyle>) {
        this.beerStyles = beerStyles
        notifyDataSetChanged()
    }
}