package com.beehive.beerrate.model

import com.google.gson.annotations.SerializedName

data class Beer(
    val objectId: String,
    val beerStyle: BeerStyle,
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val styleScore: Double,
    val overallScore: Double,
    val averageQuickRating: Double,
    val normalizedAverageReviwe: Double,
    val averageReview: Double,
    val calories: Int,
    val brewer: Brewer
)

// Need EmbeddedBeerList and BeerList classes for GsonConverterFactory
// to be able to produce an array of beers when calling /api/beers.
// Maybe we need to change the API.
data class EmbeddedBeerList(
    @SerializedName("_embedded")
    val embedded: BeerList
)

data class BeerList(
    val beers: List<Beer>
)