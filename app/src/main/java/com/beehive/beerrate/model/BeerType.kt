package com.beehive.beerrate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beertypes")
data class BeerType(
    @PrimaryKey
    @ColumnInfo(name = "beertype_id")
    val uid: Int,
    var preferred: Boolean,
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