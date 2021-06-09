package com.beehive.beerrate.ui.beerbottomsheet

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.beehive.beerrate.R
import com.beehive.beerrate.model.Beer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class BeerBottomSheetFragment(private val beer: Beer) : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        BottomSheetDialog(requireContext(), theme)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("BottomSheet", "onCreateView")
        val root = inflater.inflate(R.layout.bottom_sheet_beer_dialog, container, false)
        // Using view/data binding to reduce the number of findViewById would be nice.
        val nameTextView = root.findViewById<TextView>(R.id.bottom_sheet_name_textview)
        val locationAndManfTextView = root.findViewById<TextView>(R.id.bottom_sheet_location_and_manf_textview)
        val descriptionTextView = root.findViewById<TextView>(R.id.bottom_sheet_description_textview)
        val userRatingTextView = root.findViewById<TextView>(R.id.bottom_sheet_user_rating)
        val ratingBar = root.findViewById<RatingBar>(R.id.ratingBar)
        nameTextView!!.text = beer.beerName.trim()
        locationAndManfTextView!!.text = "Made by ${beer.brewerName} from ${beer.city}, ${beer.name}".trim()
        descriptionTextView!!.text = beer.description
        userRatingTextView!!.text = DecimalFormat("#.##").format(beer.normalizedAverageReview)
        ratingBar!!.rating = beer.normalizedAverageReview.toFloat()
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("BottomSheet", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BottomSheet", "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("BottomSheet", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("BottomSheet", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("BottomSheet", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("BottomSheet", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("BottomSheet", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("BottomSheet", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BottomSheet", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("BottomSheet", "onDetach")
    }
}