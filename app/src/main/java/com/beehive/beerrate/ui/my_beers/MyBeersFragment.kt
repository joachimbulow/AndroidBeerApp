package com.beehive.beerrate.ui.my_beers

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.helper.observeOnce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyBeersFragment : Fragment() {

    private lateinit var myBeersViewModel: MyBeersViewModel
    private lateinit var prefBeerRecycleView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBeersViewModel =
            ViewModelProvider(this).get(MyBeersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_beers, container, false)
        prefBeerRecycleView = root.findViewById(R.id.pref_beer_recyclerView)
        prefBeerRecycleView.adapter = PrefBeerAdapter(emptyList())
        prefBeerRecycleView.layoutManager = LinearLayoutManager(activity)
        prefBeerRecycleView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        // Makes sure the onCreateOptionsMenu is invoked.
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myBeersViewModel.prefBeers.observeOnce(viewLifecycleOwner, Observer { t ->
            val adapter = PrefBeerAdapter(t)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return true
                }

            })
            prefBeerRecycleView.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_beer_menu, menu)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
    }

}