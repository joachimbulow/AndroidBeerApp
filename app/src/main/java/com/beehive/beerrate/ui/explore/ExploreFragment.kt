package com.beehive.beerrate.ui.explore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.beehive.beerrate.R
import com.beehive.beerrate.ui.preference.PreferenceViewModel
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(), CardStackListener {


    private lateinit var viewModel: ExploreViewModel
    private lateinit var cardStackView: CardStackView
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
        initialize()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allNonPreferredBeers.observe(viewLifecycleOwner, Observer {
            cardStackView.adapter = CardStackAdapter(viewModel.allNonPreferredBeers.value!!.shuffled())
        })
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        TODO("Not yet implemented")
    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardCanceled() {
        TODO("Not yet implemented")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCardRewound() {
        TODO("Not yet implemented")
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
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

}