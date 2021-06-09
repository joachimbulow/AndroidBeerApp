package com.beehive.beerrate.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.beehive.beerrate.R
import com.beehive.beerrate.helper.observeOnce
import com.beehive.beerrate.ui.beerbottomsheet.BeerBottomSheetFragment
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
    private lateinit var infoButton: FloatingActionButton
    private val manager by lazy { CardStackLayoutManager(activity, this) } // initialized using the supplied lambda upon first use, unless a value had been previously assigned.


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        cardStackView = root.findViewById(R.id.card_stack_view)
        cardStackView.adapter = CardStackAdapter(emptyList())

        prefButton = root.findViewById(R.id.explore_pref_button)
        skipButton = root.findViewById(R.id.explore_skip_button)
        infoButton = root.findViewById(R.id.explore_info_button)

        initialize()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allNonPreferredBeers.observeOnce(viewLifecycleOwner, Observer { t ->
            adapter = CardStackAdapter(t)
            cardStackView.adapter = adapter
            checkCount()
        })

        prefButton.setOnClickListener {
            cardStackView.swipe()
            preferTopBeer(0)
        }

        skipButton.setOnClickListener { cardStackView.swipe() }

        infoButton.setOnClickListener { showBottomSheetBeerDialog() }
    }


    private fun showBottomSheetBeerDialog() {
        if (adapter.itemCount <= manager.topPosition) return
        BeerBottomSheetFragment(adapter.getItem(manager.topPosition)).show(parentFragmentManager, "BOTTOM")
    }

    override fun onCardDisappeared(view: View?, position: Int) = checkCount()

    override fun onCardDragging(direction: Direction?, ratio: Float) = Unit

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Left) {
            preferTopBeer(-1)
        }
    }

    private fun preferTopBeer(offset: Int) {
        if (adapter.itemCount <= manager.topPosition) return
        val beer = adapter.getItem(manager.topPosition + offset)
        beer.preferred = true
        viewModel.updateBeer(beer)

    }

    override fun onCardCanceled() = Unit

    override fun onCardAppeared(view: View?, position: Int) = Unit

    override fun onCardRewound() = Unit

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