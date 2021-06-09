package com.beehive.beerrate.ui.preference

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.BeerType

class BeerTypeAdapter(private var beerTypes: List<BeerType>) : RecyclerView.Adapter<BeerTypeAdapter.BeerTypeViewHolder>() {

    var editMode = false

    inner class BeerTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerTypeTextView: TextView = itemView.findViewById(R.id.beer_type_textView)

        fun bind(beerType: BeerType) {
            if (editMode) {
                beerTypeTextView.text = beerType.type
                setEntryStyle(beerType)

                beerTypeTextView.setOnClickListener {
                    beerType.preferred = !beerType.preferred
                    setEntryStyle(beerType)
                }
            } else {
                beerTypeTextView.text = itemView.resources.getString(R.string.beer_type, "", beerType.type)
                beerTypeTextView.setTextColor(-1979711488) // Default gray text color.
                beerTypeTextView.setOnClickListener {}
            }
        }

        private fun setEntryStyle(beerType: BeerType) {
            beerTypeTextView.setTextColor(if (beerType.preferred) Color.parseColor("#2e7d32") else Color.RED)
            beerTypeTextView.text = itemView.resources.getString(R.string.beer_type, if (beerType.preferred) "✔   " else "✖   ", beerType.type)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerTypeViewHolder {
        return BeerTypeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.beer_type_textview, parent, false))
    }

    override fun getItemCount(): Int = beerTypes.size

    override fun onBindViewHolder(holder: BeerTypeViewHolder, position: Int) = holder.bind(beerTypes[position])

    fun setBeerTypes(beerTypes: List<BeerType>) {
        this.beerTypes = beerTypes
        notifyDataSetChanged()
    }
}