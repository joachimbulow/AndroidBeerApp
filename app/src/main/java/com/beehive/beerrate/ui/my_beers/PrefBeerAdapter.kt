package com.beehive.beerrate.ui.my_beers

import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.ui.beerbottomsheet.BeerBottomSheetFragment
import com.bumptech.glide.Glide
import java.util.*

class PrefBeerAdapter(var beers: List<Beer>, val fragmentManager: FragmentManager) : RecyclerView.Adapter<PrefBeerAdapter.BeerPrefViewHolder>(), Filterable {

    private var filteredBeers: MutableList<Beer> = mutableListOf(*beers.toTypedArray())

    inner class BeerPrefViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerNameTextView: TextView = itemView.findViewById(R.id.pref_beer_name)
        private val beerLocationTextView: TextView = itemView.findViewById(R.id.pref_beer_location)
        private val image: ImageView = itemView.findViewById(R.id.pref_beer_image)

        fun bind(beer: Beer) {
            beerNameTextView.text = beer.beerName
            beerLocationTextView.text = "${beer.city}, ${beer.name}".trim()
            Glide.with(image).load(beer.imageUrl).into(image)

            itemView.setOnClickListener { BeerBottomSheetFragment(beer).show(fragmentManager, "MyBeerBottomSheet") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerPrefViewHolder {
        return BeerPrefViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pref_beer, parent, false))
    }

    override fun getItemCount(): Int = filteredBeers.size

    override fun onBindViewHolder(holder: BeerPrefViewHolder, position: Int) = holder.bind(filteredBeers[position])

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val tempList = mutableListOf<Beer>()
                val search = constraint.toString().toLowerCase().trim()
                if (search.isEmpty()) {
                    tempList.addAll(beers as MutableList<Beer>)
                } else {
                    for (beer in beers) {
                        if (beer.beerName.toLowerCase(Locale.ROOT).contains(search)) {
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