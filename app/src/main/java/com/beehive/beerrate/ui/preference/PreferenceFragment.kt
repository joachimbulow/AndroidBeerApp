package com.beehive.beerrate.ui.preference

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.BeerStyle
import com.beehive.beerrate.model.BeerType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PreferenceFragment : Fragment() {

    private lateinit var preferenceViewModel: PreferenceViewModel
    private lateinit var beerTypesRecyclerView: RecyclerView
    private lateinit var beerStylesRecyclerView: RecyclerView
    private lateinit var editBeerTypesButton: Button
    private lateinit var editBeerStyleButton: Button
    private lateinit var toggleBeerStyleButton: Button
    private var beerTypesEditMode = false
    private var beerStylesEditMode = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferenceViewModel = ViewModelProvider(this).get(PreferenceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_preference, container, false)

        beerTypesRecyclerView = root.findViewById(R.id.preferences_types_of_beer_recyclerView)
        beerTypesRecyclerView.adapter = BeerTypeAdapter(emptyList())
        beerTypesRecyclerView.layoutManager = LinearLayoutManager(activity)

        beerStylesRecyclerView = root.findViewById(R.id.preferences_styles_of_beer_recyclerView)
        beerStylesRecyclerView.adapter = BeerStyleAdapter(emptyList())
        beerStylesRecyclerView.layoutManager = LinearLayoutManager(activity)
        beerStylesRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        editBeerTypesButton = root.findViewById(R.id.preferences_types_of_beer_edit)
        editBeerStyleButton = root.findViewById(R.id.preferences_styles_of_beer_edit)
        toggleBeerStyleButton = root.findViewById(R.id.preferences_types_of_beer_toggle)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceViewModel.preferredBeerTypes.observe(viewLifecycleOwner, Observer((beerTypesRecyclerView.adapter as BeerTypeAdapter)::setBeerTypes))

        preferenceViewModel.preferredBeerStyles.observe(viewLifecycleOwner, Observer((beerStylesRecyclerView.adapter as BeerStyleAdapter)::setBeerStyles))

        preferenceViewModel.allBeerTypes.observe(viewLifecycleOwner, Observer { beerTypes ->
            editBeerTypesButton.setOnClickListener {
                if (beerTypesEditMode) {
                    preferenceViewModel.updateBeerTypePreferences()
                    beerTypesEditMode = false
                    editBeerStyleButton.isEnabled = true
                    editBeerTypesButton.text = getString(R.string.edit)
                } else {
                    beerTypesEditMode = true
                    editBeerStyleButton.isEnabled = false
                    editBeerTypesButton.text = getString(R.string.save)
                }
                updateBeerTypesAdapter(beerTypes)
                toggleBeerStyleButton.isEnabled = !beerTypesEditMode
            }
            toggleBeerStyleButton.setOnClickListener {
                val visibility =
                    if (beerTypesRecyclerView.visibility == View.GONE) View.VISIBLE else View.GONE
                beerTypesRecyclerView.visibility = visibility
                editBeerTypesButton.isEnabled = visibility == View.VISIBLE
            }
        })

        preferenceViewModel.allBeerStyles.observe(viewLifecycleOwner, Observer { beerStyles ->
            editBeerStyleButton.setOnClickListener {
                if (beerStylesEditMode) {
                    preferenceViewModel.updateBeerStylePreferences()
                    beerStylesEditMode = false
                    editBeerTypesButton.isEnabled = beerTypesRecyclerView.visibility == View.VISIBLE
                    toggleBeerStyleButton.isEnabled = true
                    editBeerStyleButton.text = getString(R.string.edit)
                    beerStylesRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL), 0)
                } else {
                    beerStylesEditMode = true
                    editBeerTypesButton.isEnabled = false
                    toggleBeerStyleButton.isEnabled = false
                    editBeerStyleButton.text = getString(R.string.save)
                    beerStylesRecyclerView.removeItemDecorationAt(0)
                }
                updateBeerStylesAdapter(beerStyles)
            }
        })
    }

    private fun updateBeerTypesAdapter(beerTypes: List<BeerType>) {
        (beerTypesRecyclerView.adapter as BeerTypeAdapter).apply {
            edit = beerTypesEditMode
            setBeerTypes(beerTypes)
        }
    }

    private fun updateBeerStylesAdapter(beerStyles: List<BeerStyle>) {
        (beerStylesRecyclerView.adapter as BeerStyleAdapter).apply {
            edit = beerStylesEditMode
            setBeerStyles(beerStyles)
        }
    }
}