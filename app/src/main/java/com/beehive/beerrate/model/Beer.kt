package com.beehive.beerrate.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beers")
data class Beer(
    @ColumnInfo(name = "beer_id")
    @PrimaryKey
    val uid: Int,
    val id: String,
    @ColumnInfo(name = "query_id")
    val queryId: String,
    @ColumnInfo(name = "beer_name")
    val beerName: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val description: String? = "",
    @ColumnInfo(name = "style_score")
    val styleScore: Double,
    @ColumnInfo(name = "overall_score")
    val overallScore: Double,
    @ColumnInfo(name = "average_quick_rating")
    val averageQuickRating: Double,
    @ColumnInfo(name = "normalized_average_review")
    val normalizedAverageReview: Double,
    @ColumnInfo(name = "average_review")
    val averageReview: Double,
    val calories: Int,
    val preferred: Int,
    @ColumnInfo(name = "brewer_name")
    val brewerName: String,
    val name: String,
    val code: String,
    val city: String,
    @ColumnInfo(name = "beerstyle_id")
    val beerstyleId: Int
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