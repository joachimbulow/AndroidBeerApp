package com.beehive.beerrate.service

import android.content.Context
import android.content.SharedPreferences
import com.beehive.beerrate.model.BeerStyle
import com.beehive.beerrate.model.BeerType
import com.google.gson.Gson

class BeerPreference(context: Context) {

    private val gson = Gson()
    private val PREFS_NAME = "beer_preference"
    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        const val BEERTYPES_KEY = "beertypes"
        const val BEERSTYLE_KEY = "beerstyles"
    }

    fun setBeerTypes(beerTypes: List<BeerType>) {
        preferences[BEERTYPES_KEY] = gson.toJson(beerTypes)
    }

    fun getBeerTypes(): List<BeerType> {
        return gson.fromJson(preferences[BEERTYPES_KEY] ?: "", Array<BeerType>::class.java).toList()
    }

    fun clearBeerTypes() {
        setBeerTypes(emptyList())
    }

    fun setBeerStyles(beerStyles: List<BeerStyle>) {
        preferences[BEERSTYLE_KEY] = gson.toJson(beerStyles)
    }

    fun getBeerStyles(): List<BeerStyle> {
        return gson.fromJson(preferences[BEERSTYLE_KEY] ?: "", Array<BeerStyle>::class.java)
            .toList()
    }

    fun clearBeerStyles() {
        setBeerStyles(emptyList())
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    private operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    private inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}