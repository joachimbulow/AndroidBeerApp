package com.beehive.beerrate.model

import com.google.gson.annotations.SerializedName

data class BeerStyle(
    val objectId: String,
    val queryId: Int,
    val name: String,
    val description: String,
    val beerType: BeerType
)

data class EmbeddedBeerStyleList(
    @SerializedName("_embedded")
    val embedded: BeerStyleList
)

data class BeerStyleList(
    @SerializedName("beerstyles")
    val beerStyles: List<BeerStyle>
)