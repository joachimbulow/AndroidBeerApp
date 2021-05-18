package com.beehive.beerrate.ui.preference

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.helper.ResolveAttrColor
import com.beehive.beerrate.model.BeerType

class BeerTypeEditAdapter(var allBeerTypes: List<BeerType>) :
    RecyclerView.Adapter<BeerTypeEditAdapter.EditBeerTypeViewHolder>() {

    inner class EditBeerTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerTypeTextView: TextView = itemView.findViewById(R.id.beer_type_textView)
        private var originalTextViewColor: Int = 0

        fun bind(beerType: BeerType) {
            beerTypeTextView.text = beerType.type
            originalTextViewColor = beerTypeTextView.currentTextColor
            beerTypeTextView.setBackgroundColor(if(beerType.preferred) ResolveAttrColor.resolve(beerTypeTextView.context,R.attr.colorPrimary) else 0)
            beerTypeTextView.setTextColor(if (beerType.preferred) Color.WHITE else originalTextViewColor)

            beerTypeTextView.setOnClickListener {
                beerType.preferred = !beerType.preferred
                beerTypeTextView.setBackgroundColor(if(beerType.preferred) ResolveAttrColor.resolve(beerTypeTextView.context,R.attr.colorPrimary) else 0)
                beerTypeTextView.setTextColor(if (beerType.preferred) Color.WHITE else originalTextViewColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditBeerTypeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.beer_type_textview, parent, false)
        return EditBeerTypeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allBeerTypes.size
    }

    override fun onBindViewHolder(holder: EditBeerTypeViewHolder, position: Int) {
        return holder.bind(allBeerTypes[position])
    }
}