package com.beehive.beerrate.helper

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import com.beehive.beerrate.R

class ResolveAttrColor {

    companion object {
        fun resolve(context: Context, @AttrRes attr: Int): Int {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            return typedValue.data
        }
    }
}