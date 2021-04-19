package com.beehive.beerrate.model

import com.google.gson.annotations.SerializedName

data class BeerReview(
    val objectId: String,
    val beer: Beer,
    val id: String,
    val comment: String,
    val score: Double,
    val scores: Scores,
    val author: Author,
    val servedIn: String,
    val likeCount: Int,
    val likedByMe: Boolean
)

data class EmbeddedBeerReviewList(
    @SerializedName("_embedded")
    val embedded: BeerReviewList
)

data class BeerReviewList(
    @SerializedName("beerreviews")
    val beerReviews: List<BeerReview>
)