package com.beehive.beerrate.ui.explore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.helper.observeOnce
import com.beehive.beerrate.model.Beer
import com.beehive.beerrate.ui.preference.PreferenceViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(), CardStackListener {


    private lateinit var viewModel: ExploreViewModel
    private lateinit var cardStackView: CardStackView
    private lateinit var adapter: CardStackAdapter
    private lateinit var prefButton: FloatingActionButton
    private lateinit var skipButton: FloatingActionButton
    private val manager by lazy { CardStackLayoutManager(activity, this) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        cardStackView = root.findViewById(R.id.card_stack_view)
        cardStackView.adapter = CardStackAdapter(emptyList())

        prefButton = root.findViewById(R.id.explore_pref_button)
        skipButton = root.findViewById(R.id.explore_skip_button)

        initialize()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allNonPreferredBeers.observeOnce(viewLifecycleOwner, Observer {
            adapter = CardStackAdapter(viewModel.allNonPreferredBeers.value!!)
            cardStackView.adapter = adapter

            checkCount()
        })

        prefButton.setOnClickListener {
            cardStackView.swipe()
            updateTopBeer(0)
        }

        skipButton.setOnClickListener {
            cardStackView.swipe()
        }

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        checkCount()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Right) {

        } else if (direction == Direction.Left) {
            updateTopBeer(-1)
        }
    }

    fun updateTopBeer(offset: Int) {
        val beer = adapter.getItem(manager.topPosition + offset)
        beer.preferred = true
        viewModel.updateBeer(listOf(beer))
        Log.d("PREF BEER", beer.toString())
    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }

    private fun checkCount() {
        if (adapter.itemCount < 1) {
            skipButton.isEnabled = false
            prefButton.isEnabled = false
        }
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }
}