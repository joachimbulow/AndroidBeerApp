package com.beehive.beerrate.ui.preference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
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
        beerStylesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        editBeerTypesButton = root.findViewById(R.id.preferences_types_of_beer_edit)
        editBeerStyleButton = root.findViewById(R.id.preferences_styles_of_beer_edit)
        toggleBeerStyleButton = root.findViewById(R.id.preferences_types_of_beer_toggle)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceViewModel.preferredBeerTypes.observe(viewLifecycleOwner, Observer {t ->
            beerTypesRecyclerView.adapter =
                BeerTypeAdapter(t)
        })

        preferenceViewModel.preferredBeerStyles.observe(viewLifecycleOwner, Observer {t ->
            beerStylesRecyclerView.adapter =
                BeerStyleAdapter(t)
        })

        preferenceViewModel.allBeerTypes.observe(viewLifecycleOwner, Observer {t->
            editBeerTypesButton.setOnClickListener {
                if (beerTypesEditMode) {
                    preferenceViewModel.updateBeerTypePreferences()
                    beerTypesEditMode = false
                    editBeerStyleButton.isEnabled = true
                    editBeerTypesButton.text = getString(R.string.edit)
                    beerTypesRecyclerView.adapter =
                        BeerTypeAdapter(t)
                } else {
                    beerTypesEditMode = true
                    editBeerStyleButton.isEnabled = false
                    editBeerTypesButton.text = getString(R.string.save)
                    beerTypesRecyclerView.adapter =
                        BeerTypeEditAdapter(t)

                }
                toggleBeerStyleButton.isEnabled = !beerTypesEditMode
            }
            toggleBeerStyleButton.setOnClickListener {
                val visibility =
                    if (beerTypesRecyclerView.visibility == View.GONE) View.VISIBLE else View.GONE
                beerTypesRecyclerView.visibility = visibility
                editBeerTypesButton.isEnabled = visibility == View.VISIBLE
            }
        })

        preferenceViewModel.allBeerStyles.observe(viewLifecycleOwner, Observer {t->
            editBeerStyleButton.setOnClickListener {
                if (beerStylesEditMode) {
                    preferenceViewModel.updateBeerStylePreferences()
                    beerStylesEditMode = false
                    editBeerTypesButton.isEnabled = true
                    editBeerStyleButton.text = getString(R.string.edit)
                    beerStylesRecyclerView.addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    beerStylesRecyclerView.adapter =
                        BeerStyleAdapter(t)
                } else {
                    beerStylesEditMode = true
                    editBeerTypesButton.isEnabled = false
                    editBeerStyleButton.text = getString(R.string.save)
                    beerStylesRecyclerView.adapter =
                        BeerStyleEditAdapter(t)
                    beerStylesRecyclerView.removeItemDecorationAt(0)
                }
            }
        })
    }
}