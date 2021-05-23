package com.beehive.beerrate.ui.my_beers

import android.util.Log
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.Beer
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog

class PrefBeerAdapter(var beers: List<Beer>) :
    RecyclerView.Adapter<PrefBeerAdapter.BeerPrefViewHolder>(), Filterable {
    private var filteredBeers: MutableList<Beer> = mutableListOf(*beers.toTypedArray())


    inner class BeerPrefViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerNameTextView: TextView = itemView.findViewById(R.id.pref_beer_name)
        private val beerLocationTextView: TextView =
            itemView.findViewById(R.id.pref_beer_location)
        private val image: ImageView = itemView.findViewById(R.id.pref_beer_image)


        fun bind(beer: Beer) {
            beerNameTextView.text = beer.beerName
            beerLocationTextView.text = "${beer.city}, ${beer.name}".trim()
            Glide.with(image).load(beer.imageUrl).into(image)

            itemView.setOnClickListener {
                val dialog = BottomSheetDialog(itemView.context, R.style.AppBottomSheetDialogTheme)
                dialog.setContentView(R.layout.bottom_sheet_beer_dialog)

                val nameTextView: TextView? = dialog.findViewById(R.id.bottom_sheet_name_textview)
                val locationAndManfTextView: TextView? =
                    dialog.findViewById(R.id.bottom_sheet_location_and_manf_textview)
                val descriptionTextView: TextView? =
                    dialog.findViewById(R.id.bottom_sheet_description_textview)

                nameTextView!!.text = beer.beerName.trim()
                locationAndManfTextView!!.text =
                    "Made by ${beer.brewerName} from ${beer.city}, ${beer.name}".trim()
                descriptionTextView!!.text = beer.description

                dialog.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerPrefViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pref_beer, parent, false)
        return BeerPrefViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredBeers.size
    }

    override fun onBindViewHolder(holder: BeerPrefViewHolder, position: Int) {
        return holder.bind(filteredBeers[position])
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val tempList = mutableListOf<Beer>()
                val search = constraint.toString().toLowerCase().trim()
                if (search.isEmpty()) {
                    tempList.addAll(beers as MutableList<Beer>)
                } else {
                    for (beer in beers) {
                        if (beer.beerName.toLowerCase().contains(search)) {
                            tempList.add(beer)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = tempList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredBeers.clear()
                filteredBeers.addAll(results!!.values as Collection<Beer>)
                notifyDataSetChanged()
            }

        }
    }
}