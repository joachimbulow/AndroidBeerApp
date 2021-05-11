package com.beehive.beerrate.service

import com.beehive.beerrate.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerService {

    @GET("beers")
    suspend fun getBeers(): EmbeddedBeerList

    @GET("beers/{objectId}")
    suspend fun getBeerByObjectId(@Path("objectId") objectId: String): Beer

    @GET("beerstyles")
    suspend fun getBeerStyles(): EmbeddedBeerStyleList

    @GET("beerstyles/{objectId}")
    suspend fun getBeerStyleByObjectId(@Path("objectId") objectId: String): BeerStyle

    @GET("beerreviews")
    suspend fun getBeerReviews(): EmbeddedBeerReviewList

    @GET("beerreviews/{objectId}")
    suspend fun getBeerReviewByObjectId(@Path("objectId") objectId: String): BeerReview

    @GET("beertypes")
    suspend fun getBeerTypes(): EmbeddedBeerTypeList

    @GET("beertypes/{objectId}")
    suspend fun getBeerTypeByObjectId(@Path("objectId") objectId: String): BeerType
}