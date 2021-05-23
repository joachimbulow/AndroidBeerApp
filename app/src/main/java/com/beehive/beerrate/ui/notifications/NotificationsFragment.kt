package com.beehive.beerrate.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var searchedBeersRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

        // Init recycler view
        searchedBeersRecyclerView = root.findViewById(R.id.searchBeerRecyclerView)
        searchedBeersRecyclerView.adapter = BeerAdapter(emptyList());
        searchedBeersRecyclerView.layoutManager = LinearLayoutManager(activity)

        //Init search button
        var searchEditText: EditText = root.findViewById(R.id.searchEditText)
        var button: Button = root.findViewById(R.id.searchBtn)
        button.setOnClickListener({
            println("Searching for: " + searchEditText.text.toString())
            notificationsViewModel.searchForBeer(searchEditText.text.toString())
        })

        //Rest
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationsViewModel.beerObservable.observe(viewLifecycleOwner, {
        searchedBeersRecyclerView.adapter = BeerAdapter(notificationsViewModel.beerObservable.value!!)
    })


    }

}