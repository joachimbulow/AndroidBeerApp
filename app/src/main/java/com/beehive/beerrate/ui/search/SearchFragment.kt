package com.beehive.beerrate.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchedBeersRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

        // Init recycler view
        searchedBeersRecyclerView = root.findViewById(R.id.searchBeerRecyclerView)
        searchedBeersRecyclerView.adapter = BeerAdapter(emptyList(), searchViewModel);
        searchedBeersRecyclerView.layoutManager = LinearLayoutManager(activity)

        val decoration: DividerItemDecoration = DividerItemDecoration(searchedBeersRecyclerView.context, LinearLayoutManager.VERTICAL)

        //Init search button
        var searchEditText: EditText = root.findViewById(R.id.searchEditText)
        var button: Button = root.findViewById(R.id.searchBtn)
        button.setOnClickListener {
            if (searchEditText.text.length < 3) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Please input at least 3 characters",
                    Toast.LENGTH_SHORT
                ).show();
            } else {
                searchViewModel.searchForBeer(searchEditText.text.toString())
            }
        }

        //Rest
        searchViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.beerObservable.observe(viewLifecycleOwner, {
        searchedBeersRecyclerView.adapter = BeerAdapter(searchViewModel.beerObservable.value!!, searchViewModel)
    })


    }

}