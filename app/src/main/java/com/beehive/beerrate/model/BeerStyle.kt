package com.beehive.beerrate.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beerstyles")
data class BeerStyle(
    @PrimaryKey
    @ColumnInfo(name = "beerstyle_id")
    val uid: Int,
    @ColumnInfo(name="query_id")
    val queryId: Int,
    @ColumnInfo(name = "beerstyle_name")
    val name: String,
    val description: String,
    @ColumnInfo(name = "beertype_id")
    val beerTypeId: Int,
    var preferred: Boolean
)

data class EmbeddedBeerStyleList(
    @SerializedName("_embedded")
    val embedded: BeerStyleList
)

data class BeerStyleList(
    @SerializedName("beerstyles")
    val beerStyles: List<BeerStyle>
)