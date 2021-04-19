package com.beehive.beerrate.service

import com.beehive.beerrate.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerService {

    @GET("beers")
    fun getBeers(): Call<EmbeddedBeerList>

    @GET("beers/{objectId}")
    fun getBeerByObjectId(@Path("objectId") objectId: String): Call<Beer>

    @GET("beerstyles")
    fun getBeerStyles(): Call<EmbeddedBeerStyleList>

    @GET("beerstyles/{objectId}")
    fun getBeerStyleByObjectId(@Path("objectId") objectId: String): Call<BeerStyle>

    @GET("beerreviews")
    fun getBeerReviews(): Call<EmbeddedBeerReviewList>

    @GET("beerreviews/{objectId}")
    fun getBeerReviewByObjectId(@Path("objectId") objectId: String): Call<BeerReview>

    @GET("beertypes")
    fun getBeerTypes(): Call<EmbeddedBeerTypeList>

    @GET("beertypes/{objectId}")
    fun getBeerTypeByObjectId(@Path("objectId") objectId: String): Call<BeerType>
}