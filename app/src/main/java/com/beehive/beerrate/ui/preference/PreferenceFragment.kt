package com.beehive.beerrate.ui.preference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferenceFragment : Fragment() {

    private lateinit var preferenceViewModel: PreferenceViewModel
    private lateinit var beerTypesRecyclerView: RecyclerView
    private lateinit var beerStylesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferenceViewModel =
            ViewModelProvider(this).get(PreferenceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_preference, container, false)

        beerTypesRecyclerView =
            root.findViewById(R.id.preferences_types_of_beer_recyclerView)
        beerTypesRecyclerView.adapter = BeerTypeAdapter(emptyList())
        beerTypesRecyclerView.layoutManager = LinearLayoutManager(activity)

        beerStylesRecyclerView = root.findViewById(R.id.preferences_styles_of_beer_recyclerView)
        beerStylesRecyclerView.adapter = BeerStyleAdapter(emptyList())
        beerStylesRecyclerView.layoutManager = LinearLayoutManager(activity)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceViewModel.beerTypes.observe(viewLifecycleOwner) {
            beerTypesRecyclerView.adapter =
                BeerTypeAdapter(preferenceViewModel.beerTypes.value!!)
        }

        preferenceViewModel.beerStyles.observe(viewLifecycleOwner) {
            beerStylesRecyclerView.adapter =
                BeerStyleAdapter(preferenceViewModel.beerStyles.value!!)
        }
    }
}