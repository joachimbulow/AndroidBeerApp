package com.beehive.beerrate.model

import com.google.gson.annotations.SerializedName

data class BeerType(
    val objectId: String,
    val type: String
)

data class EmbeddedBeerTypeList(
    @SerializedName("_embedded")
    val embedded: BeerTypeList
)

data class BeerTypeList(
    @SerializedName("beertypes")
    val beerTypes: List<BeerType>
)